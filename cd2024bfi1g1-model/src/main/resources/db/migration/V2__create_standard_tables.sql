CREATE TABLE smp_measures(
    msr_id serial PRIMARY KEY,
    msr_cel_temp DECIMAL(5, 2) NOT NULL,
    msr_hum DECIMAL(5, 2) NOT NULL,
    msr_in_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    msr_mac_dev VARCHAR(17) NOT NULL
);