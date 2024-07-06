ALTER TABLE IF EXISTS assessment_groups 
ALTER COLUMN objective TYPE oid USING CASE 
    WHEN objective ~ '^\d+$' THEN objective::oid
    ELSE NULL -- or a specific oid value if applicable
END;
ALTER TABLE IF EXISTS assessments 
ALTER COLUMN observation TYPE oid USING CASE 
    WHEN observation ~ '^\d+$' THEN observation::oid
    ELSE '0'::oid -- Replace '0' with your default oid value
END;
ALTER TABLE IF EXISTS clients 
ALTER COLUMN notes TYPE oid USING CASE 
    WHEN notes ~ '^\d+$' THEN notes::oid
    ELSE '0'::oid -- Replace '0' with your default oid value or NULL if appropriate
END;
ALTER TABLE IF EXISTS components 
ALTER COLUMN definition TYPE oid USING CASE 
    WHEN definition ~ '^\d+$' THEN definition::oid
    ELSE NULL
END;
ALTER TABLE IF EXISTS components 
ALTER COLUMN explanation TYPE oid USING CASE 
    WHEN explanation ~ '^\d+$' THEN explanation::oid
    ELSE NULL -- Replace NULL with a default oid value if necessary
END;