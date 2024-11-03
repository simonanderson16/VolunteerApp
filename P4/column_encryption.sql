USE FinalProject
GO

-- create master key
CREATE MASTER KEY ENCRYPTION BY PASSWORD = 'StrongPassword123!';

-- create certificate
CREATE CERTIFICATE PersonCert
WITH SUBJECT = 'Certificate for Password Encryption';

-- create symmetric key
CREATE SYMMETRIC KEY PasswordKey
WITH ALGORITHM = AES_256
ENCRYPTION BY CERTIFICATE PersonCert;

-- add encrypted password column
ALTER TABLE person
ADD encrypted_password VARBINARY(256);

-- open symmetric key
OPEN SYMMETRIC KEY PasswordKey
DECRYPTION BY CERTIFICATE PersonCert;

-- add encrypted password to each entry
UPDATE person
SET encrypted_password = ENCRYPTBYKEY(Key_GUID('PasswordKey'), [password]);

-- close key
CLOSE SYMMETRIC KEY PasswordKey;

-- make sure encrypted column was added
SELECT person_id, [password], encrypted_password
FROM person;

-- decrypt to make sure everything is working
OPEN SYMMETRIC KEY PasswordKey
DECRYPTION BY CERTIFICATE PersonCert;

-- retrieve data with decrypted password
SELECT
    person_id,
    [name],
    email,
    [password],
    CONVERT(NVARCHAR(255), DecryptByKey(encrypted_password)) AS decrypted_password
FROM person;

-- Close the symmetric key
CLOSE SYMMETRIC KEY PasswordKey;
