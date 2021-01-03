CREATE TABLE Trip(
    _id INTEGER NOT NULL,
    depart_id INTEGER NOT NULL,
    dest_id INTEGER NOT NULL,
    price DOUBLE(20,5) NOT NULL,
    PRIMARY KEY (_id),
    FOREIGN KEY (depart_id) REFERENCES Place(_id),
    FOREIGN KEY (dest_id) REFERENCES Place(_id)
)