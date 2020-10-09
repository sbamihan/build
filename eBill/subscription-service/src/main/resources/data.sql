INSERT INTO subscription_type (type_code, description) VALUES ('EBIL', 'Electronic sending of bill');
INSERT INTO subscription_type (type_code, description) VALUES ('OUTN', 'Outage notification');

INSERT INTO contact_type (type_code, description) VALUES ('EADD', 'Email Address');
INSERT INTO contact_type (type_code, description) VALUES ('SMSN', 'SMS Number');
INSERT INTO contact_type (type_code, description) VALUES ('PHON', 'Phone Number');

COMMIT;