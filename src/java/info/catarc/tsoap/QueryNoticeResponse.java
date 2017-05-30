
package info.catarc.tsoap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *       &lt;sequence>
 *         &lt;element name="QueryNoticeResult" type="{http://tsoap.catarc.info/}ArrayOfString" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "queryNoticeResult"
})
@XmlRootElement(name = "QueryNoticeResponse")
public class QueryNoticeResponse {

    @XmlElement(name = "QueryNoticeResult")
    protected ArrayOfString queryNoticeResult;

    /**
     * Gets the value of the queryNoticeResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getQueryNoticeResult() {
        return queryNoticeResult;
    }

    /**
     * Sets the value of the queryNoticeResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setQueryNoticeResult(ArrayOfString value) {
        this.queryNoticeResult = value;
    }

}