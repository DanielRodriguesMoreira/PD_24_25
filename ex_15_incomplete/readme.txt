MySQL Server: sql7.freemysqlhosting.net
Port number: 3306 (default)

Database name: sql7728478

Username: sql7728478
Password: wKU4XsnT1n

http://www.phpmyadmin.co

Database structure (a single table):

	CREATE TABLE pi_workers (
  		address VARCHAR(100) NOT NULL,
  		port INT NOT NULL,
  		timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  		PRIMARY KEY (address, port)
	);

