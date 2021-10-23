ALTER TABLE financial_record
ADD COLUMN paid TINYINT(1);

UPDATE financial_record
SET paid = 1
WHERE type = 'EXPENSE';

ALTER TABLE financial_record
ADD COLUMN created_at DATETIME;

ALTER TABLE financial_record
ADD COLUMN updated_at DATETIME;