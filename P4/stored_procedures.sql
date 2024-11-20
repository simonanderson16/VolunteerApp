-- Procedure for admins of an organization to create a new volunteer event
CREATE PROCEDURE RegisterEvent
    @Capacity INT,
    @Title NVARCHAR(100),
    @Description NVARCHAR(255),
    @StartTime DATETIME,
    @EndTime DATETIME,
    @Location NVARCHAR(100),
    @OrgID INT
AS
BEGIN
    INSERT INTO event (capacity, title, [description], start_time, end_time, [location], org_id)
    VALUES (@Capacity, @Title, @Description, @StartTime, @EndTime, @Location, @OrgID);
END;
GO

-- Procedure for users to sign up for an event
CREATE PROCEDURE SignUpForEvent
    @PersonID INT,
    @EventID INT,
    @Timestamp DATETIME
AS
BEGIN
    INSERT INTO sign_up (person_id, event_id, [timestamp])
    VALUES (@PersonID, @EventID, @Timestamp);
END;
GO

-- Procedure for users to sign into an event
CREATE PROCEDURE SignInToEvent
    @PersonID INT,
    @EventID INT,
    @Timestamp DATETIME
AS
BEGIN
    INSERT INTO sign_in (person_id, event_id, [timestamp])
    VALUES (@PersonID, @EventID, @Timestamp);
END;
GO

-- Procedure for adding a review to an event
CREATE PROCEDURE AddReview
    @PersonID INT,
    @EventID INT,
    @Timestamp DATETIME,
    @Title NVARCHAR(100),
    @Rating INT,
    @Content NVARCHAR(255)
AS
BEGIN
    IF (@Rating >= 1 AND @Rating <= 5)
        BEGIN
            INSERT INTO review (person_id, event_id, [timestamp], title, rating, content)
            VALUES (@PersonID, @EventID, @Timestamp, @Title, @Rating, @Content);
        END;
END;
GO
