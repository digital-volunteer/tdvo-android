package com.hackaton.hackatonapp.pojo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ParkingData {

@SerializedName("Longitude")
@Expose
private Double longitude;
@SerializedName("Latitude")
@Expose
private Double latitude;
@SerializedName("ParkingSpaceName")
@Expose
private String parkingSpaceName;
@SerializedName("StreetAddress")
@Expose
private String streetAddress;
@SerializedName("ElectricChargingAvl")
@Expose
private Boolean electricChargingAvl;
@SerializedName("NoOfSpots")
@Expose
private Integer noOfSpots;
@SerializedName("ParkingCost")
@Expose
private String parkingCost;
@SerializedName("SpecialParking")
@Expose
private String specialParking;
@SerializedName("ExtraInfo")
@Expose
private String extraInfo;
@SerializedName("TimeUpdated")
@Expose
private String timeUpdated;
@SerializedName("DateUpdated")
@Expose
private String dateUpdated;
@SerializedName("ParkingStartTime")
@Expose
private String parkingStartTime;
@SerializedName("ParkingEndTime")
@Expose
private String parkingEndTime;
@SerializedName("Chance")
@Expose
private Integer chance;

public Double getLongitude() {
return longitude;
}

public void setLongitude(Double longitude) {
this.longitude = longitude;
}

public Double getLatitude() {
return latitude;
}

public void setLatitude(Double latitude) {
this.latitude = latitude;
}

public String getParkingSpaceName() {
return parkingSpaceName;
}

public void setParkingSpaceName(String parkingSpaceName) {
this.parkingSpaceName = parkingSpaceName;
}

public String getStreetAddress() {
return streetAddress;
}

public void setStreetAddress(String streetAddress) {
this.streetAddress = streetAddress;
}

public Boolean getElectricChargingAvl() {
return electricChargingAvl;
}

public void setElectricChargingAvl(Boolean electricChargingAvl) {
this.electricChargingAvl = electricChargingAvl;
}

public Integer getNoOfSpots() {
return noOfSpots;
}

public void setNoOfSpots(Integer noOfSpots) {
this.noOfSpots = noOfSpots;
}

public String getParkingCost() {
return parkingCost;
}

public void setParkingCost(String parkingCost) {
this.parkingCost = parkingCost;
}

public String getSpecialParking() {
return specialParking;
}

public void setSpecialParking(String specialParking) {
this.specialParking = specialParking;
}

public String getExtraInfo() {
return extraInfo;
}

public void setExtraInfo(String extraInfo) {
this.extraInfo = extraInfo;
}

public String getTimeUpdated() {
return timeUpdated;
}

public void setTimeUpdated(String timeUpdated) {
this.timeUpdated = timeUpdated;
}

public String getDateUpdated() {
return dateUpdated;
}

public void setDateUpdated(String dateUpdated) {
this.dateUpdated = dateUpdated;
}

public String getParkingStartTime() {
return parkingStartTime;
}

public void setParkingStartTime(String parkingStartTime) {
this.parkingStartTime = parkingStartTime;
}

public String getParkingEndTime() {
return parkingEndTime;
}

public void setParkingEndTime(String parkingEndTime) {
this.parkingEndTime = parkingEndTime;
}

public Integer getChance() {
return chance;
}

public void setChance(Integer chance) {
this.chance = chance;
}

}
