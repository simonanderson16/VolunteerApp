USE FinalProject
GO

DROP TABLE organization_location;
DROP TABLE organization_tag;
DROP TABLE event_tag;
DROP TABLE tag;
DROP TABLE review;
DROP TABLE sign_up;
DROP TABLE sign_in;
DROP TABLE belongs_to;

-- Drop tables with foreign key dependencies next
DROP TABLE event;
DROP TABLE organization;
DROP TABLE person;
DROP TABLE emergency_contact;