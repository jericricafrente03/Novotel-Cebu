package com.ph.bittelasia.mesh.tv.libFragment.view.fragment;


import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.squareup.picasso.Picasso;

import java.util.HashMap;

public abstract class WeatherFragment extends BasicFragment {

    private String location;
    private String temp;
    private String date;
    private String  countryCode;
    private String weatherURI;


    private TextView locationView;
    private TextView tempView;
    private TextView dateView;
    private TextClock clockView;
    private ImageView weatherView;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void getView(View v) {
        locationView = v.findViewById(getLocationViewID());
        tempView = v.findViewById(getTempViewID());
        dateView = v.findViewById(getDateViewID());
        clockView = v.findViewById(getCLockViewID());
        weatherView = v.findViewById(getWeatherViewID());
        updateDetails(false);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void updateDetails(final boolean isReset)
    {
        try {
            if(getActivity()!=null) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (!isReset) {
                                if (getLocation() != null)
                                    locationView.setText(getLocation());
                                if (getTemp() != null)
                                    tempView.setText(getTemp());
                                if (getDate() != null)
                                    dateView.setText(getDate());
                                if (getCountryCode() != null)
                                    clockView.setTimeZone(getTimezone(getCountryCode()));
                                if (getWeatherURI() != null)
                                    Picasso.get().load(getWeatherURI()).into(weatherView);
                            } else {
                                locationView.setText("");
                                tempView.setText("");
                                dateView.setText("");
                                clockView.setText("");
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public void setWeatherURI(String weatherURI) {
        this.weatherURI = weatherURI;
    }

    public String getLocation() {
        return location;
    }

    public String getTemp() {
        return temp;
    }

    public String getDate() {
        return date;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getWeatherURI() {
        return weatherURI;
    }

    public TextView getLocationView() {
        return locationView;
    }

    public TextView getTempView() {
        return tempView;
    }

    public TextView getDateView() {
        return dateView;
    }

    public TextClock getClockView() {
        return clockView;
    }

    public ImageView getWeatherView() {
        return weatherView;
    }

    static String getTimezone(String countryISOCode) {
        HashMap<String, String> cities = new HashMap();
        cities.put("AD", "Europe/Andorra");
        cities.put("AE", "Asia/Dubai");
        cities.put("AF", "Asia/Kabul");
        cities.put("AG", "America/Antigua");
        cities.put("AI", "America/Anguilla");
        cities.put("AL", "Europe/Tirane");
        cities.put("AM", "Asia/Yerevan");
        cities.put("AO", "Africa/Luanda");
        cities.put("AQ", "Antarctica/Casey");
        cities.put("AQ", "Antarctica/Davis");
        cities.put("AQ", "Antarctica/Macquarie");
        cities.put("AQ", "Antarctica/Mawson");
        cities.put("AQ", "Antarctica/Palmer");
        cities.put("AQ", "Antarctica/Rothera");
        cities.put("AQ", "Antarctica/South_Pole");
        cities.put("AQ", "Antarctica/Syowa");
        cities.put("AQ", "Antarctica/Troll");
        cities.put("AQ", "Antarctica/Vostok");
        cities.put("AR", "America/Argentina/Buenos_Aires");
        cities.put("AR", "America/Argentina/Catamarca");
        cities.put("AR", "America/Argentina/Cordoba");
        cities.put("AR", "America/Argentina/Jujuy");
        cities.put("AR", "America/Argentina/La_Rioja");
        cities.put("AR", "America/Argentina/Mendoza");
        cities.put("AR", "America/Argentina/Rio_Gallegos");
        cities.put("AR", "America/Argentina/Salta");
        cities.put("AR", "America/Argentina/San_Juan");
        cities.put("AR", "America/Argentina/San_Luis");
        cities.put("AR", "America/Argentina/Tucuman");
        cities.put("AR", "America/Argentina/Ushuaia");
        cities.put("AS", "Pacific/Pago_Pago");
        cities.put("AS", "Pacific/Samoa");
        cities.put("AT", "Europe/Vienna");
        cities.put("AU", "Australia/ACT");
        cities.put("AU", "Australia/Adelaide");
        cities.put("AU", "Australia/Brisbane");
        cities.put("AU", "Australia/Broken_Hill");
        cities.put("AU", "Australia/Canberra");
        cities.put("AU", "Australia/Currie");
        cities.put("AU", "Australia/Darwin");
        cities.put("AU", "Australia/Eucla");
        cities.put("AU", "Australia/Hobart");
        cities.put("AU", "Australia/LHI");
        cities.put("AU", "Australia/Lindeman");
        cities.put("AU", "Australia/Lord_Howe");
        cities.put("AU", "Australia/Melbourne");
        cities.put("AU", "Australia/North");
        cities.put("AU", "Australia/NSW");
        cities.put("AU", "Australia/Perth");
        cities.put("AU", "Australia/Queensland");
        cities.put("AU", "Australia/South");
        cities.put("AU", "Australia/Sydney");
        cities.put("AU", "Australia/Tasmania");
        cities.put("AU", "Australia/Victoria");
        cities.put("AU", "Australia/West");
        cities.put("AU", "Australia/Yancowinna");
        cities.put("AW", "America/Aruba");
        cities.put("AX", "Europe/Mariehamn");
        cities.put("AZ", "Asia/Baku");
        cities.put("BA", "Europe/Sarajevo");
        cities.put("BB", "America/Barbados");
        cities.put("BD", "Asia/Dhaka");
        cities.put("BE", "Europe/Brussels");
        cities.put("BF", "Africa/Ouagadougou");
        cities.put("BG", "Europe/Sofia");
        cities.put("BH", "Asia/Bahrain");
        cities.put("BI", "Africa/Bujumbura");
        cities.put("BJ", "Africa/Porto-Novo");
        cities.put("BL", "America/St_Barthelemy");
        cities.put("BM", "Atlantic/Bermuda");
        cities.put("BN", "Asia/Brunei");
        cities.put("BO", "America/La_Paz");
        cities.put("BQ", "America/Bonaire");
        cities.put("BR", "America/Araguaina");
        cities.put("BR", "America/Bahia");
        cities.put("BR", "America/Belem");
        cities.put("BR", "America/Boa_Vista");
        cities.put("BR", "America/Campo_Grande");
        cities.put("BR", "America/Cuiaba");
        cities.put("BR", "America/Eirunepe");
        cities.put("BR", "America/Fortaleza");
        cities.put("BR", "America/Maceio");
        cities.put("BR", "America/Manaus");
        cities.put("BR", "America/Noronha");
        cities.put("BR", "America/Porto_Velho");
        cities.put("BR", "America/Recife");
        cities.put("BR", "America/Rio_Branco");
        cities.put("BR", "America/Santarem");
        cities.put("BR", "America/Sao_Paulo");
        cities.put("BS", "America/Nassau");
        cities.put("BT", "Asia/Thimphu");
        cities.put("BV", "Antarctica/Bouvet");
        cities.put("BW", "Africa/Gaborone");
        cities.put("BY", "Europe/Minsk");
        cities.put("BZ", "America/Belize");
        cities.put("CA", "America/Atikokan");
        cities.put("CA", "America/Blanc-Sablon");
        cities.put("CA", "America/Cambridge_Bay");
        cities.put("CA", "America/Coral_Harbour");
        cities.put("CA", "America/Dawson");
        cities.put("CA", "America/Dawson_Creek");
        cities.put("CA", "America/Edmonton");
        cities.put("CA", "America/Glace_Bay");
        cities.put("CA", "America/Goose_Bay");
        cities.put("CA", "America/Halifax");
        cities.put("CA", "America/Inuvik");
        cities.put("CA", "America/Iqaluit");
        cities.put("CA", "America/Moncton");
        cities.put("CA", "America/Montreal");
        cities.put("CA", "America/Nipigon");
        cities.put("CA", "America/Pangnirtung");
        cities.put("CA", "America/Rainy_River");
        cities.put("CA", "America/Rankin_Inlet");
        cities.put("CA", "America/Regina");
        cities.put("CA", "America/Resolute");
        cities.put("CA", "America/St_Johns");
        cities.put("CA", "America/Swift_Current");
        cities.put("CA", "America/Thunder_Bay");
        cities.put("CA", "America/Toronto");
        cities.put("CA", "America/Vancouver");
        cities.put("CA", "America/Whitehorse");
        cities.put("CA", "America/Winnipeg");
        cities.put("CA", "America/Yellowknife");
        cities.put("CC", "Indian/Cocos");
        cities.put("CD", "Africa/Kinshasa");
        cities.put("CD", "Africa/Lubumbashi");
        cities.put("CF", "Africa/Bangui");
        cities.put("CG", "Africa/Brazzaville");
        cities.put("CH", "Europe/Zurich");
        cities.put("CI", "Africa/Abidjan");
        cities.put("CK", "Pacific/Rarotonga");
        cities.put("CL", "America/Santiago");
        cities.put("CL", "Pacific/Easter");
        cities.put("CM", "Africa/Douala");
        cities.put("CN", "Asia/Chongqing");
        cities.put("CN", "Asia/Harbin");
        cities.put("CN", "Asia/Kashgar");
        cities.put("CN", "Asia/Shanghai");
        cities.put("CN", "Asia/Urumqi");
        cities.put("CO", "America/Bogota");
        cities.put("CR", "America/Costa_Rica");
        cities.put("CU", "America/Havana");
        cities.put("CV", "Atlantic/Cape_Verde");
        cities.put("CW", "America/Curacao");
        cities.put("CX", "Indian/Christmas");
        cities.put("CY", "Asia/Nicosia");
        cities.put("CZ", "Europe/Prague");
        cities.put("DE", "Europe/Berlin");
        cities.put("DJ", "Africa/Djibouti");
        cities.put("DK", "Europe/Copenhagen");
        cities.put("DM", "America/Dominica");
        cities.put("DO", "America/Santo_Domingo");
        cities.put("DZ", "Africa/Algiers");
        cities.put("EC", "America/Guayaquil");
        cities.put("EC", "Pacific/Galapagos");
        cities.put("EE", "Europe/Tallinn");
        cities.put("EG", "Africa/Cairo");
        cities.put("EH", "Africa/El_Aaiun");
        cities.put("ER", "Africa/Asmara");
        cities.put("ES", "Africa/Ceuta");
        cities.put("ES", "Atlantic/Canary");
        cities.put("ES", "Europe/Madrid");
        cities.put("ET", "Africa/Addis_Ababa");
        cities.put("FI", "Europe/Helsinki");
        cities.put("FJ", "Pacific/Fiji");
        cities.put("FK", "Atlantic/Stanley");
        cities.put("FM", "Pacific/Chuuk");
        cities.put("FM", "Pacific/Kosrae");
        cities.put("FM", "Pacific/Pohnpei");
        cities.put("FO", "Atlantic/Faroe");
        cities.put("FR", "Europe/Paris");
        cities.put("GA", "Africa/Libreville");
        cities.put("GB", "Europe/London");
        cities.put("GD", "America/Grenada");
        cities.put("GE", "Asia/Tbilisi");
        cities.put("GF", "America/Cayenne");
        cities.put("GG", "Europe/Guernsey");
        cities.put("GH", "Africa/Accra");
        cities.put("GI", "Europe/Gibraltar");
        cities.put("GL", "America/Danmarkshavn");
        cities.put("GL", "America/Godthab");
        cities.put("GL", "America/Scoresbysund");
        cities.put("GL", "America/Thule");
        cities.put("GM", "Africa/Banjul");
        cities.put("GN", "Africa/Conakry");
        cities.put("GP", "America/Guadeloupe");
        cities.put("GQ", "Africa/Malabo");
        cities.put("GR", "Europe/Athens");
        cities.put("GS", "Atlantic/South_Georgia");
        cities.put("GT", "America/Guatemala");
        cities.put("GU", "Pacific/Guam");
        cities.put("GW", "Africa/Bissau");
        cities.put("GY", "America/Guyana");
        cities.put("HK", "Asia/Hong_Kong");
        cities.put("HM", "Australia/Heard_and_McDonald");
        cities.put("HN", "America/Tegucigalpa");
        cities.put("HR", "Europe/Zagreb");
        cities.put("HT", "America/Port-au-Prince");
        cities.put("HU", "Europe/Budapest");
        cities.put("ID", "Asia/Jakarta");
        cities.put("ID", "Asia/Jayapura");
        cities.put("ID", "Asia/Makassar");
        cities.put("ID", "Asia/Pontianak");
        cities.put("IE", "Europe/Dublin");
        cities.put("IL", "Asia/Jerusalem");
        cities.put("IM", "Europe/Isle_of_Man");
        cities.put("IN", "Asia/Kolkata");
        cities.put("IO", "Indian/Chagos");
        cities.put("IQ", "Asia/Baghdad");
        cities.put("IR", "Asia/Tehran");
        cities.put("IS", "Atlantic/Reykjavik");
        cities.put("IT", "Europe/Rome");
        cities.put("JE", "Europe/Jersey");
        cities.put("JM", "America/Jamaica");
        cities.put("JO", "Asia/Amman");
        cities.put("JP", "Asia/Tokyo");
        cities.put("KE", "Africa/Nairobi");
        cities.put("KG", "Asia/Bishkek");
        cities.put("KH", "Asia/Phnom_Penh");
        cities.put("KI", "Pacific/Enderbury");
        cities.put("KI", "Pacific/Kiritimati");
        cities.put("KI", "Pacific/Tarawa");
        cities.put("KM", "Indian/Comoro");
        cities.put("KN", "America/St_Kitts");
        cities.put("KP", "Asia/Pyongyang");
        cities.put("KR", "Asia/Seoul");
        cities.put("KW", "Asia/Kuwait");
        cities.put("KY", "America/Cayman");
        cities.put("KZ", "Asia/Almaty");
        cities.put("KZ", "Asia/Aqtau");
        cities.put("KZ", "Asia/Aqtobe");
        cities.put("KZ", "Asia/Oral");
        cities.put("KZ", "Asia/Qyzylorda");
        cities.put("LA", "Asia/Vientiane");
        cities.put("LB", "Asia/Beirut");
        cities.put("LC", "America/St_Lucia");
        cities.put("LI", "Europe/Vaduz");
        cities.put("LK", "Asia/Colombo");
        cities.put("LR", "Africa/Monrovia");
        cities.put("LS", "Africa/Maseru");
        cities.put("LT", "Europe/Vilnius");
        cities.put("LU", "Europe/Luxembourg");
        cities.put("LV", "Europe/Riga");
        cities.put("LY", "Africa/Tripoli");
        cities.put("MA", "Africa/Casablanca");
        cities.put("MC", "Europe/Monaco");
        cities.put("MD", "Europe/Chisinau");
        cities.put("ME", "Europe/Podgorica");
        cities.put("MF", "America/Marigot");
        cities.put("MG", "Indian/Antananarivo");
        cities.put("MH", "Pacific/Kwajalein");
        cities.put("MH", "Pacific/Majuro");
        cities.put("MK", "Europe/Skopje");
        cities.put("ML", "Africa/Bamako");
        cities.put("MM", "Asia/Rangoon");
        cities.put("MN", "Asia/Choibalsan");
        cities.put("MN", "Asia/Hovd");
        cities.put("MN", "Asia/Ulaanbaatar");
        cities.put("MO", "Asia/Macau");
        cities.put("MP", "Pacific/Saipan");
        cities.put("MQ", "America/Martinique");
        cities.put("MR", "Africa/Nouakchott");
        cities.put("MS", "America/Montserrat");
        cities.put("MT", "Europe/Malta");
        cities.put("MU", "Indian/Mauritius");
        cities.put("MV", "Indian/Maldives");
        cities.put("MW", "Africa/Blantyre");
        cities.put("MX", "America/Bahia_Banderas");
        cities.put("MX", "America/Cancun");
        cities.put("MX", "America/Chihuahua");
        cities.put("MX", "America/Hermosillo");
        cities.put("MX", "America/Matamoros");
        cities.put("MX", "America/Mazatlan");
        cities.put("MX", "America/Merida");
        cities.put("MX", "America/Mexico_City");
        cities.put("MX", "America/Monterrey");
        cities.put("MX", "America/Ojinaga");
        cities.put("MX", "America/Santa_Isabel");
        cities.put("MX", "America/Tijuana");
        cities.put("MY", "Asia/Kuala_Lumpur");
        cities.put("MY", "Asia/Kuching");
        cities.put("MZ", "Africa/Maputo");
        cities.put("NA", "Africa/Windhoek");
        cities.put("NC", "Pacific/Noumea");
        cities.put("NE", "Africa/Niamey");
        cities.put("NF", "Pacific/Norfolk");
        cities.put("NG", "Africa/Lagos");
        cities.put("NI", "America/Managua");
        cities.put("NL", "Europe/Amsterdam");
        cities.put("NO", "Europe/Oslo");
        cities.put("NP", "Asia/Kathmandu");
        cities.put("NR", "Pacific/Nauru");
        cities.put("NU", "Pacific/Niue");
        cities.put("NZ", "Pacific/Auckland");
        cities.put("NZ", "Pacific/Chatham");
        cities.put("OM", "Asia/Muscat");
        cities.put("PA", "America/Panama");
        cities.put("PE", "America/Lima");
        cities.put("PF", "Pacific/Gambier");
        cities.put("PF", "Pacific/Marquesas");
        cities.put("PF", "Pacific/Tahiti");
        cities.put("PG", "Pacific/Port_Moresby");
        cities.put("PH", "Asia/Manila");
        cities.put("PK", "Asia/Karachi");
        cities.put("PL", "Europe/Warsaw");
        cities.put("PM", "America/Miquelon");
        cities.put("PN", "Pacific/Pitcairn");
        cities.put("PR", "America/Puerto_Rico");
        cities.put("PS", "Asia/Gaza");
        cities.put("PT", "Atlantic/Azores");
        cities.put("PT", "Atlantic/Madeira");
        cities.put("PT", "Europe/Lisbon");
        cities.put("PW", "Pacific/Palau");
        cities.put("PY", "America/Asuncion");
        cities.put("QA", "Asia/Qatar");
        cities.put("RE", "Indian/Reunion");
        cities.put("RO", "Europe/Bucharest");
        cities.put("RS", "Europe/Belgrade");
        cities.put("RU", "Asia/Anadyr");
        cities.put("RU", "Asia/Irkutsk");
        cities.put("RU", "Asia/Kamchatka");
        cities.put("RU", "Asia/Krasnoyarsk");
        cities.put("RU", "Asia/Magadan");
        cities.put("RU", "Asia/Novokuznetsk");
        cities.put("RU", "Asia/Novosibirsk");
        cities.put("RU", "Asia/Omsk");
        cities.put("RU", "Asia/Sakhalin");
        cities.put("RU", "Asia/Vladivostok");
        cities.put("RU", "Asia/Yakutsk");
        cities.put("RU", "Asia/Yekaterinburg");
        cities.put("RU", "Europe/Kaliningrad");
        cities.put("RU", "Europe/Moscow");
        cities.put("RU", "Europe/Samara");
        cities.put("RU", "Europe/Volgograd");
        cities.put("RW", "Africa/Kigali");
        cities.put("SA", "Asia/Riyadh");
        cities.put("SB", "Pacific/Guadalcanal");
        cities.put("SC", "Indian/Mahe");
        cities.put("SD", "Africa/Khartoum");
        cities.put("SE", "Europe/Stockholm");
        cities.put("SG", "Asia/Singapore");
        cities.put("SH", "Atlantic/St_Helena");
        cities.put("SI", "Europe/Ljubljana");
        cities.put("SJ", "Arctic/Longyearbyen");
        cities.put("SK", "Europe/Bratislava");
        cities.put("SL", "Africa/Freetown");
        cities.put("SM", "Europe/San_Marino");
        cities.put("SN", "Africa/Dakar");
        cities.put("SO", "Africa/Mogadishu");
        cities.put("SR", "America/Paramaribo");
        cities.put("SS", "Africa/Juba");
        cities.put("ST", "Africa/Sao_Tome");
        cities.put("SV", "America/El_Salvador");
        cities.put("SX", "America/Sint-Maarten");
        cities.put("SY", "Asia/Damascus");
        cities.put("SZ", "Africa/Mbabane");
        cities.put("TC", "America/Grand_Turk");
        cities.put("TD", "Africa/Ndjamena");
        cities.put("TF", "Indian/Kerguelen");
        cities.put("TG", "Africa/Lome");
        cities.put("TH", "Asia/Bangkok");
        cities.put("TJ", "Asia/Dushanbe");
        cities.put("TK", "Pacific/Fakaofo");
        cities.put("TL", "Asia/Dili");
        cities.put("TM", "Asia/Ashgabat");
        cities.put("TN", "Africa/Tunis");
        cities.put("TO", "Pacific/Tongatapu");
        cities.put("TR", "Europe/Istanbul");
        cities.put("TT", "America/Port_of_Spain");
        cities.put("TV", "Pacific/Funafuti");
        cities.put("TW", "Asia/Taipei");
        cities.put("TZ", "Africa/Dar_es_Salaam");
        cities.put("UA", "Europe/Kiev");
        cities.put("UA", "Europe/Simferopol");
        cities.put("UA", "Europe/Uzhgorod");
        cities.put("UA", "Europe/Zaporozhye");
        cities.put("UG", "Africa/Kampala");
        cities.put("UM", "Pacific/Johnston");
        cities.put("UM", "Pacific/Midway");
        cities.put("UM", "Pacific/Wake");
        cities.put("US", "America/Adak");
        cities.put("US", "America/Anchorage");
        cities.put("US", "America/Boise");
        cities.put("US", "America/Chicago");
        cities.put("US", "America/Denver");
        cities.put("US", "America/Detroit");
        cities.put("US", "America/Indiana/Indianapolis");
        cities.put("US", "America/Indiana/Knox");
        cities.put("US", "America/Indiana/Marengo");
        cities.put("US", "America/Indiana/Petersburg");
        cities.put("US", "America/Indiana/Tell_City");
        cities.put("US", "America/Indiana/Vevay");
        cities.put("US", "America/Indiana/Vincennes");
        cities.put("US", "America/Indiana/Winamac");
        cities.put("US", "America/Juneau");
        cities.put("US", "America/Kentucky/Louisville");
        cities.put("US", "America/Kentucky/Monticello");
        cities.put("US", "America/Los_Angeles");
        cities.put("US", "America/Menominee");
        cities.put("US", "America/New_York");
        cities.put("US", "America/Nome");
        cities.put("US", "America/North_Dakota/Beulah");
        cities.put("US", "America/North_Dakota/Center");
        cities.put("US", "America/North_Dakota/New_Salem");
        cities.put("US", "America/Phoenix");
        cities.put("US", "America/Yakutat");
        cities.put("US", "Atlantic");
        cities.put("US", "Pacific/Honolulu");
        cities.put("UY", "America/Montevideo");
        cities.put("UZ", "Asia/Samarkand");
        cities.put("UZ", "Asia/Tashkent");
        cities.put("VA", "Europe/Vatican");
        cities.put("VC", "America/St_Vincent");
        cities.put("VE", "America/Caracas");
        cities.put("VG", "America/Tortola");
        cities.put("VI", "America/St_Thomas");
        cities.put("VN", "Asia/Ho_Chi_Minh");
        cities.put("VU", "Pacific/Efate");
        cities.put("WF", "Pacific/Wallis");
        cities.put("WS", "Pacific/Apia");
        cities.put("XK", "Europe/Pristina");
        cities.put("YE", "Asia/Aden");
        cities.put("YT", "Indian/Mayotte");
        cities.put("ZA", "Africa/Johannesburg");
        cities.put("ZM", "Africa/Lusaka");
        cities.put("ZW", "Africa/Harare");
        String result = (String)cities.get(countryISOCode);
        return result;
    }

    public abstract int getLocationViewID();
    public abstract int getTempViewID();
    public abstract int getDateViewID();
    public abstract int getCLockViewID();
    public abstract int getWeatherViewID();

}
