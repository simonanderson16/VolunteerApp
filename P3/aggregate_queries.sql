USE FinalProject

-- Query 1: Aggregate Query - Count the number of volunteers signed up for a specific event (1 is a placeholder)
SELECT event_id, COUNT(*) AS total_volunteers_signed_up
FROM sign_up
WHERE event_id = 1
GROUP BY event_id;

-- Query 2: Aggregate Query - Find the average rating for each event
SELECT event_id, AVG(rating) AS average_rating
FROM review
GROUP BY event_id;

-- Query 3: Join Query - Get a list of all events with their corresponding organization names
SELECT e.event_id, e.title, o.name AS organization_name
FROM event e
    JOIN organization o ON e.org_id = o.org_id;

-- Query 4: Join Query - List all people who signed up for a particular event, including their names and the event title
SELECT p.name AS person_name, e.title AS event_title
FROM sign_up s
    JOIN person p ON s.person_id = p.person_id
    JOIN event e ON s.event_id = e.event_id
WHERE e.event_id = 1;

-- Query 5: Subquery - Find the organizations that have hosted more than 5 events
SELECT name
FROM organization
WHERE org_id IN (
    SELECT org_id
FROM event
GROUP BY org_id
HAVING COUNT(event_id) > 5
);

-- Query 6: Subquery - Find events where the number of sign-ups is less than 10
SELECT event_id
FROM event
WHERE event_id IN (
    SELECT event_id
FROM sign_up
GROUP BY event_id
HAVING COUNT(person_id) < 10
);

-- Query 7: Aggregate Query - Find the maximum number of people who signed up for an event
SELECT TOP 1
    event_id, COUNT(person_id) AS total_sign_ups
FROM sign_up
GROUP BY event_id
ORDER BY total_sign_ups DESC;


-- Query 8: Join Query - Show the number of reviews for each event, including the event title
SELECT e.title, COUNT(r.person_id) AS total_reviews
FROM event e
    LEFT JOIN review r ON e.event_id = r.event_id
GROUP BY e.event_id, e.title;

-- Query 9: Subquery - Find the number of spots remaining for each event
SELECT
    e.event_id,
    e.title AS event_name,
    e.capacity - (
        SELECT COUNT(*)
        FROM sign_up s
        WHERE s.event_id = e.event_id
    ) AS spots_remaining
FROM event e;

-- Query 10: Aggregate Query - Find the different tags that organizations have
SELECT t.name AS tag_name, COUNT(DISTINCT ot.org_id) AS organization_count
FROM organization_tag ot
    JOIN tag t ON ot.tag_id = t.tag_id
GROUP BY t.name;