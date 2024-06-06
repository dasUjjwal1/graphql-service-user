CREATE
OR REPLACE FUNCTION cdm.insert_agent_and_user(
    ta_name CHARACTER VARYING,
    ta_mobile CHARACTER VARYING,
    ta_password CHARACTER VARYING
) RETURNS BIGINT AS $ BODY $ DECLARE created_on TIMESTAMP;

user_id BIGINT;

BEGIN created_on := current_timestamp;

INSERT INTO
    cdm.tbl_agent("name", mobile_number, created_on)
VALUES
    (ta_name, ta_mobile, created_on);

INSERT INTO
    cdm.tbl_user("name", mobile, "password", created_on)
VALUES
    (ta_name, ta_mobile, ta_password, created_on) RETURNING "id" INTO user_id;

RETURN user_id;

END;

$ BODY $ LANGUAGE plpgsql