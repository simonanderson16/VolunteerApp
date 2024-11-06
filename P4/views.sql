-- View to show event details with organization details
CREATE VIEW vEventDetails AS
SELECT 
    e.event_id, 
    e.title, 
    e.capacity, 
    e.start_time, 
    e.end_time, 
    e.location, 
    o.name AS organization_name, 
    o.email AS organization_email
FROM event e
INNER JOIN organization o ON e.org_id = o.org_id;
GO

-- View to display the signups for an event (useful for organization admins)
CREATE VIEW vSignUpsByEvent AS
SELECT 
    e.event_id, 
    e.title AS event_title, 
    p.person_id, 
    p.name AS person_name, 
    su.timestamp AS sign_up_time
FROM sign_up su
INNER JOIN person p ON su.person_id = p.person_id
INNER JOIN event e ON su.event_id = e.event_id;
GO

-- View to display all members of an organization along with their role
CREATE VIEW vOrganizationMembers AS
SELECT 
    o.org_id,
    o.name AS organization_name,
    p.person_id,
    p.name AS person_name,
    bt.role
FROM belongs_to bt
INNER JOIN person p ON bt.person_id = p.person_id
INNER JOIN organization o ON bt.org_id = o.org_id;
GO
