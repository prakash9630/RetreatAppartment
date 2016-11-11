package project.revision.tap.retre.Pojo;

import java.io.Serializable;

/**
 * Created by prakash on 10/26/2016.
 */
public class Available_rooms_getter implements Serializable {

    String ApartmentName;
    Double BookingPrice;
    String Body;
    String Image;
    int UnitNo;


    public Available_rooms_getter(String apartmentName, Double bookingPrice, String body, String image, int unitNo) {
        ApartmentName = apartmentName;
        BookingPrice = bookingPrice;
        Body = body;
        Image = image;
        UnitNo = unitNo;
    }

    public String getApartmentName() {
        return ApartmentName;
    }

    public void setApartmentName(String apartmentName) {
        ApartmentName = apartmentName;
    }

    public Double getBookingPrice() {
        return BookingPrice;
    }

    public void setBookingPrice(Double bookingPrice) {
        BookingPrice = bookingPrice;
    }

    public String getBody() {
        return Body;
    }

    public void setBody(String body) {
        Body = body;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public int getUnitNo() {
        return UnitNo;
    }

    public void setUnitNo(int unitNo) {
        UnitNo = unitNo;
    }
}
