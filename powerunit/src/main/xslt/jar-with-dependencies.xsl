<?xml version="1.0" ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns="http://maven.apache.org/POM/4.0.0" version="2.0">
	<xsl:output method="xml" indent="no"/>
	<xsl:template match="/*[local-name()='project']">
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
			xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
			<xsl:for-each select="*">
				<xsl:choose>
					<xsl:when test="name()='artifactId'">
<xsl:text>
	</xsl:text>
	<artifactId>powerunit-with-dependencies</artifactId><xsl:text>
</xsl:text>
					</xsl:when>
					<xsl:when test="name()='name'">
<xsl:text>
	</xsl:text>
	<name><xsl:copy-of select="text()" /> - All dependencies included</name><xsl:text>
</xsl:text>
					</xsl:when>
					<xsl:when test="name()='description'">
<xsl:text>
	</xsl:text>
	<description><xsl:copy-of select="text()" /> - All dependencies included</description><xsl:text>
</xsl:text>
					</xsl:when>
					<xsl:when test="name()='properties'"/>
					<xsl:when test="name()='build'"/>
					<xsl:when test="name()='profiles'"/>
					<xsl:when test="name()='reporting'"/>
					<xsl:when test="name()='dependencies'"/>
					<xsl:otherwise>
<xsl:text>
	</xsl:text>
	<xsl:copy-of select="."></xsl:copy-of>
  <xsl:text>
</xsl:text>
					</xsl:otherwise>
				</xsl:choose>
			</xsl:for-each>
</project>
	</xsl:template>
</xsl:stylesheet>