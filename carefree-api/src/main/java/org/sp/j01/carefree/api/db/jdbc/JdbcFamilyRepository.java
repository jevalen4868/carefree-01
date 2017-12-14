package org.sp.j01.carefree.api.db.jdbc;

import org.sp.j01.carefree.api.db.FamilyRepository;
import org.sp.j01.carefree.api.domain.Family;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class JdbcFamilyRepository implements FamilyRepository {

    private static final String INSERT_FAMILY = "insert into Family (family_name, thumbnail_photo_url, standard_photo_url, description, leaves_behind, incarcerated_date) values (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_FAMILY = "select family_id, family_name, created_at, thumbnail_photo_url, standard_photo_url, description, leaves_behind, incarcerated_date from Family";
    private JdbcTemplate jdbcTemplate;

    public JdbcFamilyRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long count() {
        return jdbcTemplate.queryForObject("select count(family_id) from Family", Long.class);
    }

    /**
     * @param family
     * @return
     */
    public Family save(Family family) {
        Long familyId = family.getFamilyId();
        if (familyId == null) {
            familyId = insertFamilyAndReturnId(family);
            return getOne(familyId);
            //return new Family(familyId, family.getFamilyName(), null, family.getThumbnailPhotoUrl(), family.getStandardPhotoUrl(), family.getDescription(), family.getLeavesBehind(), family.getIncarceratedDate());
        } else {
            jdbcTemplate.update("update Family set family_name=?, thumbnail_photo_url=?, standard_photo_url=?, description=?, leaves_behind=?, incarcerated_date=? where family_id=?",
                    family.getFamilyName(),
                    family.getThumbnailPhotoUrl(),
                    family.getStandardPhotoUrl(),
                    family.getDescription(),
                    family.getLeavesBehind(),
                    family.getIncarceratedDate(),
                    familyId);
        }
        return family;
    }

    /**
     * Inserts a family using SimpleJdbcInsert.
     * Involves no direct SQL and is able to return the ID of the newly created Family.
     *
     * @param family a Family to insert into the databse
     * @return the ID of the newly inserted Family
     */
    private long insertFamilyAndReturnId(Family family) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("Family");
        jdbcInsert.setGeneratedKeyNames("family_id");
        jdbcInsert.usingColumns("family_name", "thumbnail_photo_url", "standard_photo_url", "description", "leaves_behind", "incarcerated_date");
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("family_name", family.getFamilyName());
        args.put("thumbnail_photo_url", family.getThumbnailPhotoUrl());
        args.put("standard_photo_url", family.getStandardPhotoUrl());
        args.put("description", family.getDescription());
        args.put("leaves_behind", family.getLeavesBehind());
        args.put("incarcerated_date", family.getIncarceratedDate());
        long familyId = jdbcInsert.executeAndReturnKey(args).longValue();
        return familyId;
    }

    /**
     * Inserts a family using a simple JdbcTemplate update() call.
     * Returns the number of rows affected.
     *
     * @param family a Family to insert into the database
     */
    private int insertFamily(Family family) {
        return jdbcTemplate.update(INSERT_FAMILY,
                family.getFamilyName(),
                family.getThumbnailPhotoUrl(),
                family.getStandardPhotoUrl(),
                family.getDescription(),
                family.getLeavesBehind(),
                family.getIncarceratedDate());
    }

    public Family getOne(Long id) {
        return jdbcTemplate.queryForObject(
                SELECT_FAMILY + " where family_id=?", new FamilyRowMapper(), id);
    }

    public Family getBy(String familyName) {
        return jdbcTemplate.queryForObject(SELECT_FAMILY + " where family_name=?", new FamilyRowMapper(), familyName);
    }

    public List<Family> getAll() {
        return jdbcTemplate.query(SELECT_FAMILY + " order by family_id", new FamilyRowMapper());
    }

    private static final class FamilyRowMapper implements RowMapper<Family> {
        public Family mapRow(ResultSet rs, int rowNum) throws SQLException {
            Long id = rs.getLong("family_id");
            String familyName = rs.getString("family_name");
            Timestamp createdAtTs = rs.getTimestamp("created_at");
            LocalDateTime createdAt = createdAtTs.toLocalDateTime();
            String thumbnailPhotoUrl = rs.getString("thumbnail_photo_url");
            String standardPhotoUrl = rs.getString("standard_photo_url");
            String description = rs.getString("description");
            Integer leavesBehind = rs.getInt("leaves_behind");
            Date incarceratedSqlDate = rs.getDate("incarcerated_date");
            LocalDate incarceratedDate = incarceratedSqlDate.toLocalDate();
            return new Family(id, familyName, createdAt, thumbnailPhotoUrl, standardPhotoUrl, description, leavesBehind, incarceratedDate);
        }
    }

}
