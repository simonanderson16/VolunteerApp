
--//Adds a 'New' tag to an org when it is created. Nice for users to be able to look for new orgs. 

CREATE TRIGGER NewOrgTag
ON organization
AFTER INSERT
AS
BEGIN
    DECLARE @NewOrgId INT
    DECLARE @NewTagId INT

    SELECT @NewOrgId = org_id FROM inserted

    BEGIN
        -- Gets max tag id from tag table, adds 1 to it to make it unique
        SELECT @NewTagId = MAX(tag_id) + 1 FROM tag
        
        -- Create 'New' tag with the new tag id
        INSERT INTO tag (tag_id, name)
        VALUES (@NewTagId, 'New')
    END

    INSERT INTO organization_tag (org_id, tag_id)
    VALUES (@NewOrgId, @NewTagId)
END
GO
