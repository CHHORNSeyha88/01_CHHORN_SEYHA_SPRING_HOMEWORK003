-- Attendees Table
create table attendees(
                          attendee_id serial primary key not null,
                          attendee_name varchar(200) not null,
                          email varchar(200) not null unique check (email ~* '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$')
    );

-- Venues Table
create table venues(
                       venue_id serial primary key not null,
                       venue_name varchar(200) not null unique,
                       location varchar(250) not null
);
-- Events Table
CREATE TABLE events (
                        event_id SERIAL PRIMARY KEY,
                        event_name VARCHAR(200) NOT NULL,
                        event_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        venue_id INT NOT NULL,
                        FOREIGN KEY (venue_id) REFERENCES venues(venue_id) ON DELETE CASCADE
);

-- Event-Attendee Mapping Table
create table event_attendee(
                               event_id int not null,
                               attendee_id int not null,
                               primary key (event_id, attendee_id),
                               foreign key (attendee_id) REFERENCES attendees(attendee_id) on delete cascade,
                               foreign key (event_id) REFERENCES events(event_id) on delete cascade
);