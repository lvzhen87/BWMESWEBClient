
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
 *         &lt;element name="QueryVidByVinResult" type="{http://tsoap.catarc.info/}ArrayOfString" minOccurs="0"/>
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
    "queryVidByVinResult"
})
@XmlRootElement(name = "QueryVidByVinResponse")
public class QueryVidByVinResponse {

    @XmlElement(name = "QueryVidByVinResult")
    protected ArrayOfString queryVidByVinResult;

    /**
     * Gets the value of the queryVidByVinResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getQueryVidByVinResult() {
        return queryVidByVinResult;
    }

    /**
     * Sets the value of the queryVidByVinResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setQueryVidByVinResult(ArrayOfString value) {
        this.queryVidByVinResult = value;
    }

}
