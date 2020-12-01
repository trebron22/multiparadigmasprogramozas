DROP TABLE IF EXISTS geolocation_search;

	CREATE TABLE geolocation_search (
	  id INT AUTO_INCREMENT  PRIMARY KEY
	);
DROP TABLE IF EXISTS addresses;

	CREATE TABLE address (
	  id INT AUTO_INCREMENT  PRIMARY KEY,
	  geolocation_search_id INT REFERENCES geolocation_search(id),
	  search VARCHAR(250) NOT NULL
	);
