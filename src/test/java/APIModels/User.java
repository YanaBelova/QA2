package APIModels;

public class User {
    private String id;
    private String name;
    private String username;
    private String email;
    private Address address;
       private class Address {
           private String street;
           private String suite;
           private String city;
           private String zipcode;
           private Geo geo;
            private class Geo{
               private String lat;
               private String lng;
          }
    }
    private String phone;
    private String website;
    private Company company;
    private class Company {
        private String name;
        private String catchPhrase;
        private String bs;
    }

    public String getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getUsername(){
        return username;
    }
    public String getEmail(){
        return email;
    }
    public String getStreet(){
        return address.street;
    }
    public String getSuite(){
        return address.suite;
    }
    public String getCity(){
        return address.city;
    }
    public String getZipcode(){
        return address.zipcode;
    }
    public String getLat(){
        return address.geo.lat;
    }
    public String getLng(){
        return address.geo.lng;
    }
    public String getPhone(){
        return phone;
    }
    public String getWebsite(){
        return website;
    }
    public String getCompanyName(){
        return company.name;
    }
    public String getCatchPhrase(){
        return company.catchPhrase;
    }
    public String getBs(){
        return company.bs;
    }

    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User object = (User) o;
        if(!id.equals(object.getId())) return false;
        if(!name.equals(object.getName())) return false;
        if(!username.equals(object.getUsername())) return  false;
        if(!email.equals(object.getEmail())) return false;
        if(!address.street.equals(object.getStreet())) return false;
        if(!address.suite.equals(object.getSuite())) return false;
        if(!address.city.equals(object.getCity())) return  false;
        if(!address.zipcode.equals(object.getZipcode())) return false;
        if(!address.geo.lat.equals(object.getLat())) return false;
        if(!address.geo.lng.equals(object.getLng())) return false;
        if(!phone.equals(object.getPhone())) return false;
        if(!website.equals(object.getWebsite())) return false;
        if(!company.name.equals(object.getCompanyName())) return false;
        if(!company.catchPhrase.equals(object.getCatchPhrase())) return false;
        return company.bs.equals(object.getBs());
    }

    public int hashCode() {
        int result = id == null ? 0 : id.hashCode();
        result +=  name== null ? 0 : name.hashCode();
        result +=  username== null ? 0 : username.hashCode();
        result +=  email== null ? 0 : email.hashCode();
        result +=  address.street== null ? 0 : address.street.hashCode();
        result +=  address.suite== null ? 0 : address.suite.hashCode();
        result +=  address.city== null ? 0 : address.city.hashCode();
        result +=  address.zipcode== null ? 0 : address.zipcode.hashCode();
        result +=  address.geo.lat== null ? 0 : address.geo.lat.hashCode();
        result +=  address.geo.lng== null ? 0 : address.geo.lng.hashCode();
        result +=  phone== null ? 0 : phone.hashCode();
        result +=  website == null ? 0 : website.hashCode();
        result +=  company.name== null ? 0 : company.name.hashCode();
        result +=  company.catchPhrase== null ? 0 : company.catchPhrase.hashCode();
        result +=  company.bs== null ? 0 : company.bs.hashCode();
        return result;
    }
}
