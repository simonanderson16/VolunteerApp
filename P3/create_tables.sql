USE FinalProject
GO

CREATE TABLE emergency_contact
(
    contact_id INT UNIQUE NOT NULL IDENTITY(1,1),
    [name] NVARCHAR(100) NOT NULL,
    phone_number NVARCHAR(20) NOT NULL,
    CONSTRAINT PK_emergency_contact PRIMARY KEY (contact_id)
);

CREATE TABLE person
(
    person_id INT UNIQUE NOT NULL IDENTITY(1,1),
    [password] NVARCHAR(255) NOT NULL,
    [name] NVARCHAR(100) NOT NULL,
    email NVARCHAR(100) UNIQUE NOT NULL,
    contact_id INT NOT NULL,
    CONSTRAINT PK_person PRIMARY KEY (person_id),
    CONSTRAINT FK_person_contact FOREIGN KEY (contact_id) REFERENCES emergency_contact(contact_id)
);

CREATE TABLE organization
(
    org_id INT UNIQUE NOT NULL IDENTITY(1,1),
    [name] NVARCHAR(100) NOT NULL,
    [description] NVARCHAR(255),
    email NVARCHAR(100),
    website NVARCHAR(100),
    person_id INT NOT NULL,
    CONSTRAINT PK_organization PRIMARY KEY (org_id),
    CONSTRAINT FK_organization_person FOREIGN KEY (person_id) REFERENCES person(person_id)
);

CREATE TABLE event
(
    event_id INT UNIQUE NOT NULL IDENTITY(1,1),
    capacity INT NOT NULL,
    title NVARCHAR(100) NOT NULL,
    [description] NVARCHAR(255),
    start_time DATETIME NOT NULL,
    end_time DATETIME NOT NULL,
    [location] NVARCHAR(100),
    org_id INT NOT NULL,
    CONSTRAINT PK_event PRIMARY KEY (event_id),
    CONSTRAINT FK_event_organization FOREIGN KEY (org_id) REFERENCES organization(org_id),
    CONSTRAINT CHK_event_capacity CHECK (capacity > 0),
    CONSTRAINT CHK_event_time CHECK (start_time < end_time)
);

CREATE TABLE belongs_to
(
    person_id INT,
    org_id INT,
    role NVARCHAR(50),
    CONSTRAINT PK_belongs_to PRIMARY KEY (person_id, org_id),
    CONSTRAINT FK_belongs_to_person FOREIGN KEY (person_id) REFERENCES person(person_id),
    CONSTRAINT FK_belongs_to_organization FOREIGN KEY (org_id) REFERENCES organization(org_id)
);

CREATE TABLE sign_in
(
    person_id INT,
    event_id INT,
    [timestamp] DATETIME NOT NULL,
    CONSTRAINT PK_sign_in PRIMARY KEY (person_id, event_id),
    CONSTRAINT FK_sign_in_person FOREIGN KEY (person_id) REFERENCES person(person_id),
    CONSTRAINT FK_sign_in_event FOREIGN KEY (event_id) REFERENCES event(event_id)
);

CREATE TABLE sign_up
(
    person_id INT,
    event_id INT,
    [timestamp] DATETIME NOT NULL,
    CONSTRAINT PK_sign_up PRIMARY KEY (person_id, event_id),
    CONSTRAINT FK_sign_up_person FOREIGN KEY (person_id) REFERENCES person(person_id),
    CONSTRAINT FK_sign_up_event FOREIGN KEY (event_id) REFERENCES event(event_id)
);

CREATE TABLE review
(
    person_id INT,
    event_id INT,
    [timestamp] DATETIME NOT NULL,
    title NVARCHAR(100),
    rating INT,
    content NVARCHAR(255),
    CONSTRAINT PK_review PRIMARY KEY (person_id, event_id, [timestamp]),
    CONSTRAINT FK_review_person FOREIGN KEY (person_id) REFERENCES person(person_id),
    CONSTRAINT FK_review_event FOREIGN KEY (event_id) REFERENCES event(event_id),
    CONSTRAINT CHK_review_rating CHECK (rating >= 1 AND rating <= 5)
);

CREATE TABLE tag
(
    tag_id INT UNIQUE NOT NULL IDENTITY(1,1),
    name NVARCHAR(50) UNIQUE NOT NULL,
    CONSTRAINT PK_tag PRIMARY KEY (tag_id)
);

CREATE TABLE event_tag
(
    event_id INT,
    tag_id INT,
    CONSTRAINT PK_event_tag PRIMARY KEY (event_id, tag_id),
    CONSTRAINT FK_event_tag_event FOREIGN KEY (event_id) REFERENCES event(event_id),
    CONSTRAINT FK_event_tag_tag FOREIGN KEY (tag_id) REFERENCES tag(tag_id)
);

CREATE TABLE organization_tag
(
    org_id INT,
    tag_id INT,
    CONSTRAINT PK_organization_tag PRIMARY KEY (org_id, tag_id),
    CONSTRAINT FK_organization_tag_organization FOREIGN KEY (org_id) REFERENCES organization(org_id),
    CONSTRAINT FK_organization_tag_tag FOREIGN KEY (tag_id) REFERENCES tag(tag_id)
);

CREATE TABLE organization_location
(
    org_id INT,
    [location] NVARCHAR(100),
    CONSTRAINT PK_organization_location PRIMARY KEY (org_id, [location]),
    CONSTRAINT FK_organization_location_organization FOREIGN KEY (org_id) REFERENCES organization(org_id)
);

