insert into
        capacity_configuration
        (maximum_capacity, queue_if_maximimum_capacity_exceeds, id) 
    values
        (500, true, 'ddb10678-cdec-4784-b96f-09b90bc07d57');


insert 
    into
        deadline_configuration
        (deadline, queue_if_deadline_exceeds, id) 
    values
        ('2022-10-18', true, 'b0d3e7ac-5c82-48e8-9c17-f44126dd9408');

insert 
    into
        address
        (description, street, postal_code, village, id, country) 
    values
        ('Mehrzweckhalle Oßweil', 'Horrheimer Straße 24', '70437', 'Stuttgart', 'b14ed7ff-b4fd-4c00-9956-004aa5234d0c', 'DE');

insert 
    into
        booking_configuration
        (ask_for_birthday, ask_for_further_bookings, ask_for_sex , id) 
    values
        (false, false, false, '9d955d86-5b1c-4403-ab94-fc4c1e739adf');

insert 
    into
        pricing_configuration
        (base_price, show_pricing , id) 
    values
        (0.0, false, '41b56cab-d263-417f-997d-28e36dfa6606');

insert 
    into
        conference
        (intro, pricing_configuration_id, booking_configuration_id, address_id, capacity_configuration_id, created, created_by, deadline_configuration_id, description, end_date, start_date, state, title, id) 
    values
        ('Konferenz in Laubusch', '41b56cab-d263-417f-997d-28e36dfa6606', '9d955d86-5b1c-4403-ab94-fc4c1e739adf', 'b14ed7ff-b4fd-4c00-9956-004aa5234d0c', 'ddb10678-cdec-4784-b96f-09b90bc07d57', '2022-10-03T20:11:03.942852', 'jonas-dev', 'b0d3e7ac-5c82-48e8-9c17-f44126dd9408', 'Das ist die Beschreibung der Konferenz in Katowice.', '2022-08-20', '2022-08-22', 'PUBLISHED', 'Konferenz in Katowice', 'bf9664ea-5073-472f-97ea-26f5a1133fbb');

insert 
    into
        booking
        (booking_date, state , conference_id, id) 
    values
        ('2022-10-08 11:26:39.14893', 'REQUESTED', 'bf9664ea-5073-472f-97ea-26f5a1133fbb', 'eac5b894-925d-49a8-a91f-e2468db4e12a');

insert 
    into
        person
        (email, first_name, last_name, booking_id, id) 
    values
        ('jonas@rotert.online', 'Jonas', 'Rotert', 'eac5b894-925d-49a8-a91f-e2468db4e12a', '01a08f74-1f41-4e89-a8a4-0028c0c00b15');