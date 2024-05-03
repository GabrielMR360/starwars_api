ALTER TABLE rebelde
    ADD COLUMN quantidade_reports INT DEFAULT 0,
    ADD COLUMN traidor            BOOLEAN DEFAULT FALSE;