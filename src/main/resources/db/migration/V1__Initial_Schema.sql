-- Flyway Migration Script: V1__Initial_Schema.sql
-- Description: Creates the initial tables for users, loan applications, loans, and repayments.

-- Table for Users (Farmers, Admins)
CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,                     -- Auto-incrementing 64-bit integer ID
                       first_name VARCHAR(100) NOT NULL,            -- User's first name
                       last_name VARCHAR(100) NOT NULL,             -- User's last name
                       email VARCHAR(150) NOT NULL UNIQUE,          -- User's email address (must be unique)
                       password VARCHAR(255) NOT NULL,              -- Stores the hashed password (BCrypt)
                       phone_number VARCHAR(20),                    -- User's phone number (optional)
                       role VARCHAR(50) NOT NULL,                   -- User role (e.g., 'FARMER', 'ADMIN')
                       created_at TIMESTAMP NOT NULL,               -- Timestamp when the record was created (auditing)
                       updated_at TIMESTAMP NOT NULL                -- Timestamp when the record was last updated (auditing)
);

-- Table for Loan Applications submitted by Users
CREATE TABLE loan_applications (
                                   id BIGSERIAL PRIMARY KEY,                     -- Unique ID for the application
                                   requested_amount NUMERIC(15, 2) NOT NULL,    -- Amount requested by the farmer
                                   purpose VARCHAR(500),                        -- Reason for the loan
                                   status VARCHAR(50) NOT NULL,                 -- Current status (e.g., 'SUBMITTED', 'PENDING', 'APPROVED', 'REJECTED')
                                   admin_remarks VARCHAR(1000),                 -- Optional remarks from an admin during review
                                   user_id BIGINT NOT NULL,                     -- Foreign key linking to the user who applied
                                   created_at TIMESTAMP NOT NULL,
                                   updated_at TIMESTAMP NOT NULL,

                                   CONSTRAINT fk_loan_applications_user FOREIGN KEY (user_id) REFERENCES users(id) -- Foreign key constraint
);

-- Table for Approved Loans resulting from Applications
CREATE TABLE loans (
                       id BIGSERIAL PRIMARY KEY,                     -- Unique ID for the disbursed loan
                       principal_amount NUMERIC(15, 2) NOT NULL,    -- The actual amount disbursed
                       interest_rate NUMERIC(5, 2) NOT NULL,        -- Annual interest rate (e.g., 12.50 for 12.50%)
                       term_months INTEGER NOT NULL,                -- Duration of the loan in months
                       disbursement_date DATE,                      -- Date the loan was given to the farmer
                       first_payment_date DATE,                     -- Date the first repayment is due
                       final_payment_date DATE,                     -- Date the final repayment is due
                       status VARCHAR(50) NOT NULL,                 -- Current status (e.g., 'ACTIVE', 'PAID_OFF', 'DEFAULTED', 'PENDING_DISBURSEMENT')
                       outstanding_balance NUMERIC(15, 2),          -- Remaining balance (principal + accrued interest)
                       borrower_id BIGINT NOT NULL,                 -- Foreign key linking to the user who borrowed (same as applicant)
                       loan_application_id BIGINT UNIQUE,           -- Foreign key linking to the originating application (must be unique)
                       created_at TIMESTAMP NOT NULL,
                       updated_at TIMESTAMP NOT NULL,

                       CONSTRAINT fk_loans_borrower FOREIGN KEY (borrower_id) REFERENCES users(id),
                       CONSTRAINT fk_loans_application FOREIGN KEY (loan_application_id) REFERENCES loan_applications(id)
);

-- Table for Loan Repayments made by Users
CREATE TABLE repayments (
                            id BIGSERIAL PRIMARY KEY,                     -- Unique ID for the repayment transaction
                            payment_date DATE NOT NULL,                  -- Date the repayment was made
                            amount_paid NUMERIC(15, 2) NOT NULL,         -- Total amount paid in this transaction
                            principal_component NUMERIC(15, 2),          -- Portion of the payment applied to principal
                            interest_component NUMERIC(15, 2),           -- Portion of the payment applied to interest
                            payment_method VARCHAR(100),                 -- How the payment was made (e.g., 'M-Pesa', 'Bank Transfer')
                            transaction_reference VARCHAR(255),          -- Unique reference for the payment transaction
                            loan_id BIGINT NOT NULL,                     -- Foreign key linking to the loan being repaid
                            created_at TIMESTAMP NOT NULL,
                            updated_at TIMESTAMP NOT NULL,

                            CONSTRAINT fk_repayments_loan FOREIGN KEY (loan_id) REFERENCES loans(id)
);

-- --- Optional: Add Indexes for Performance ---
-- Indexes on foreign keys are highly recommended for performance, especially as tables grow.
CREATE INDEX idx_loan_applications_user_id ON loan_applications(user_id);
CREATE INDEX idx_loans_borrower_id ON loans(borrower_id);
CREATE INDEX idx_loans_loan_application_id ON loans(loan_application_id); -- Index on unique FK is often useful
CREATE INDEX idx_repayments_loan_id ON repayments(loan_id);
-- Index on columns used frequently in WHERE clauses (example)
CREATE INDEX idx_loan_applications_status ON loan_applications(status);
CREATE INDEX idx_loans_status ON loans(status);