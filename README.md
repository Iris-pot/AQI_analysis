# AQI_analysis
AQI air quality analysis is based on Hadoop MapReduce

[TOC]

## Introduction

Based on the MapReduce framework, analyze the air quality levels of each city.


Research Objectives:

1. Using the Air Quality Sub Index (IAQI) of PM25 as a measurement indicator, compare the air quality levels of various cities from August 2018 to June 2019.

2. Taking Beijing, Shanghai, and Chengdu as examples, using AQI as the analysis indicator, **calculate the distribution of air quality levels** during the Spring Festival in these three cities.

3. **Construct a comprehensive air quality index system**, including all 12 cities in the analysis framework, and calculate a comprehensive score for each city.

## File Description:

| File                 | Content                           |
| -------------------- | ------------------------------ |
| PM25city.txt         | Dataset                         |
| 需求及数据说明.txt   | Requirement Description                       |
| 空气质量分析报告.pdf | Analysis Report           |
| AQI                  | Comparison of air quality levels     |
| AQIClassify          | Distribution of air quality levels     |
| AQIIndex             | Air Quality Comprehensive Index System |



## Environment:

> Hadoop Version：2.6.5
>
> Virtual Machine Software：VMware 11.0.0 build-2305329 
>
> Operating system：Linux master 2.6.32-504.el6.x86_64 #1 SMP Wed Oct 15 04:27:16 UTC 2014 x86_64 x86_64 x86_64 GNU/Linux 
>
> Linux Version： CentOS release 6.6 (Final) 
>
> Hadoop colony：a master，two slavers: slaver1，slaver2 



## Data Description:
This experiment mainly relies on air quality data, which is sourced from the internet and has a data scale of 400000 rows. The period is from 0:00 on August 1, 2018, to 23:00 on June 10, 2019, with 16 fields. The fields are station number, longitude, latitude, PM25, PM10, NO2, SO2, O3-1, O3-8h, CO, AQI, level, year, month, day, hour, and city. Separate each field with a ",". Among them, the "City" field is a string type; the rest are numerical data.

In addition, cities include Beijing, Shanghai, Tianjin, Qingdao, Jinan, Xiamen, Zhengzhou, Urumqi, Chengdu, Hohhot, Haikou, and Kunming, totalling 12 cities; Each city has a different number of air quality monitoring stations, with a collection frequency of one record per hour.
