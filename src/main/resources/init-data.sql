INSERT INTO users (name, api_key, balance, discord_id, registration_date, role, approved)
VALUES
    ('Burak', 'wqdw21d3efdfdd', 50000, '135851218546327562', '2022-04-17 17:16:42.000000', 'DEVELOPER', true)
     ,('TestDev', 'fe892hnf89efn298efn2', 50000, '1337', '2022-04-17 17:16:42.000000', 'DEVELOPER', true)
;

INSERT INTO plan (description, name, period, price, owner_id)
VALUES ('This is a test subscription plan', 'Test sub plan', 'MONTHLY', 100, 1);

INSERT INTO plugin (name, approved, description, image_url, file_name, main_class, update_date, upload_date, version,
                           author_id, plan_id)
VALUES ('TestPlugin', true, 'Test plugin description', 'https://i.imgur.com/mVptKHD.png', 'test-plugin.jar', 'net.unethicalite.plugins.TestPlugin',
        '2022-04-17 17:16:41.000000', '2022-04-17 17:16:42.000000', '0.0.1', 1, 1);