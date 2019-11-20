//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-520 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.12.20 at 05:04:39 PM JST 
//


package gen.core.filters;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="sourceFilenameMask" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="targetFilenamePattern" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="sourceEncoding" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="targetEncoding" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "files")
public class Files {

    @XmlAttribute(required = true)
    protected String sourceFilenameMask;
    @XmlAttribute
    protected String targetFilenamePattern;
    @XmlAttribute
    protected String sourceEncoding;
    @XmlAttribute
    protected String targetEncoding;

    /**
     * Gets the value of the sourceFilenameMask property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceFilenameMask() {
        return sourceFilenameMask;
    }

    /**
     * Sets the value of the sourceFilenameMask property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceFilenameMask(String value) {
        this.sourceFilenameMask = value;
    }

    /**
     * Gets the value of the targetFilenamePattern property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetFilenamePattern() {
        return targetFilenamePattern;
    }

    /**
     * Sets the value of the targetFilenamePattern property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetFilenamePattern(String value) {
        this.targetFilenamePattern = value;
    }

    /**
     * Gets the value of the sourceEncoding property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceEncoding() {
        return sourceEncoding;
    }

    /**
     * Sets the value of the sourceEncoding property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceEncoding(String value) {
        this.sourceEncoding = value;
    }

    /**
     * Gets the value of the targetEncoding property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetEncoding() {
        return targetEncoding;
    }

    /**
     * Sets the value of the targetEncoding property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetEncoding(String value) {
        this.targetEncoding = value;
    }

}