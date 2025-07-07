README.md

To understand how object-relational mapping (ORM) was handled in Hibernate using XML configuration files before the introduction of annotations in JPA and Spring Boot.

This hands-on is focused on analyzing and documenting the legacy configuration style used in traditional Hibernate applications.
Key Files
1. `hibernate.cfg.xml`
This is the main configuration file that:
- Specifies the database connection details
- Sets the Hibernate dialect
- Includes the mapping file for entity classes
Example:
xml
<hibernate-configuration>
  <session-factory>
    <property name="connection.driver_class">com.mysql.cj.jdbc.Driver</property>
    <property name="connection.url">jdbc:mysql://localhost:3306/ormlearn</property>
    <property name="connection.username">root</property>
    <property name="connection.password">root</property>
    <property name="dialect">org.hibernate.dialect.MySQLDialect</property>
    <property name="show_sql">true</property>
    <mapping resource="Country.hbm.xml"/>
  </session-factory>
</hibernate-configuration>

Comparison: XML vs. Annotations
Feature
XML Mapping (Old)
JPA Annotations (Modern)
Configuration File
hibernate.cfg.xml
application.properties
Entity Mapping File
Country.hbm.xml
@Entity, @Table, @Id
Mapping Maintenance
External .xml files
Inline in Java class
Framework
Standalone Hibernate
Spring Boot + Spring Data JPA


Conclusion
Using annotations (@Entity, @Column, @Table, etc.) in modern Spring Boot applications has replaced the need for separate XML mapping files, making configuration more intuitive, readable, and less error-prone.
This hands-on gives insight into how Hibernate ORM evolved and highlights the simplicity that Spring Boot and JPA bring to enterprise Java development.
ss