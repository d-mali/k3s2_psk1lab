package com.example.k3s2_psk1lab.mybatis.dao;

import com.example.k3s2_psk1lab.mybatis.model.Athlete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AthleteMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.ATHLETE
     *
     * @mbg.generated Mon Apr 29 03:49:47 EEST 2024
     */
    int deleteByPrimaryKey(Long athleteId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.ATHLETE
     *
     * @mbg.generated Mon Apr 29 03:49:47 EEST 2024
     */
    int insert(Athlete record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.ATHLETE
     *
     * @mbg.generated Mon Apr 29 03:49:47 EEST 2024
     */
    Athlete selectByPrimaryKey(Long athleteId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.ATHLETE
     *
     * @mbg.generated Mon Apr 29 03:49:47 EEST 2024
     */
    List<Athlete> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.ATHLETE
     *
     * @mbg.generated Mon Apr 29 03:49:47 EEST 2024
     */
    int updateByPrimaryKey(Athlete record);
}