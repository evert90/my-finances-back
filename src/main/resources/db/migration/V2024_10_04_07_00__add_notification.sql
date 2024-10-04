ALTER TABLE financial_record
ADD COLUMN notification TINYINT(1);

UPDATE financial_record
SET notification = 0
WHERE type = 'EXPENSE';

ALTER TABLE financial_record_recurrence
ADD COLUMN notification TINYINT(1);

UPDATE financial_record_recurrence
SET notification = 0
WHERE type = 'EXPENSE';
