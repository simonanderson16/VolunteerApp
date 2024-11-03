
--Fetches all the orgs the user is a part of. Useful for letting the user see where they belong. 
CREATE FUNCTION GetUserOrganizations
(
    @person_id INT
)
RETURNS TABLE
AS
RETURN
(
    SELECT 
        o.org_id,
        o.name,
        o.description,
        o.email,
        o.website,
        bt.role
    FROM organization o
    JOIN belongs_to bt ON o.org_id = bt.org_id
    WHERE bt.person_id = @person_id
)
GO

--Retrieves all the members of an organization. Can help owner or other users see who is in the org. 
CREATE FUNCTION GetOrganizationMembers
(
    @org_id INT
)
RETURNS TABLE
AS
RETURN
(
    SELECT 
        p.person_id,
        p.name,
        p.email,
        bt.role
    FROM person p
    JOIN belongs_to bt ON p.person_id = bt.person_id
    WHERE bt.org_id = @org_id
)
GO

--Fetches all events with a certain tag name. Will allow users to search for certain types of events they want to attend, like "Nature". 

CREATE FUNCTION GetEventsByTag
(
    @tag_name NVARCHAR(50)
)
RETURNS TABLE
AS
RETURN
(
    SELECT 
        e.event_id,
        e.title,
        e.description,
        e.start_time,
        e.end_time,
        e.location,
        e.capacity,
        o.name AS organization_name,
        o.org_id,
        t.name AS tag_name
    FROM event e
    JOIN event_tag et ON e.event_id = et.event_id
    JOIN tag t ON et.tag_id = t.tag_id
    JOIN organization o ON e.org_id = o.org_id
    WHERE t.name = @tag_name
)
GO

