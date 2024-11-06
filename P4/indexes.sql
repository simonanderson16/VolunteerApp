-- Non-clustered index to more efficiently lookup users by their email
CREATE NONCLUSTERED INDEX Idx_person_email ON person (email);

-- Non-clustered index to more efficiently join person onto sign_up to view names of people who have signed up
CREATE NONCLUSTERED INDEX Idx_sign_up_person ON sign_up (person_id)

-- Non-clustered index to more efficiently join organization information with event information
CREATE NONCLUSTERED INDEX Idx_event_organization ON [event] (org_id)
