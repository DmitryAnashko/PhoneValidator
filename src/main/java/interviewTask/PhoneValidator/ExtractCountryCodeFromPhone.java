package interviewTask.PhoneValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum ExtractCountryCodeFromPhone {
    Instance;

    private List<Integer> forCtryCodePrefix1 = new ArrayList<Integer>();
    @SuppressWarnings("serial")
    List<Integer> forCtryCodePrefix2 = new ArrayList<Integer>() {
        {
            add(0);
            add(7);
            add(8);

        }
    };
    @SuppressWarnings("serial")
    private List<Integer> forCtryCodePrefix3 = new ArrayList<Integer>() {
        {
            add(0);
            add(1);
            add(2);
            add(3);
            add(4);
            add(6);
            add(9);
        }
    };
    @SuppressWarnings("serial")
    private List<Integer> forCtryCodePrefix4 = new ArrayList<Integer>() {
        {
            add(0);
            add(1);
            add(3);
            add(4);
            add(5);
            add(6);
            add(7);
            add(8);
            add(9);
        }
    };
    @SuppressWarnings("serial")
    private List<Integer> forCtryCodePrefix5 = new ArrayList<Integer>() {
        {
            add(1);
            add(2);
            add(3);
            add(4);
            add(5);
            add(6);
            add(7);
            add(8);
        }
    };
    @SuppressWarnings("serial")
    private List<Integer> forCtryCodePrefix6 = new ArrayList<Integer>() {
        {
            add(0);
            add(1);
            add(3);
            add(4);
            add(5);
            add(6);

        }
    };
    private List<Integer> forCtryCodePrefix7 = new ArrayList<Integer>();
    @SuppressWarnings("serial")
    private List<Integer> forCtryCodePrefix8 = new ArrayList<Integer>() {
        {
            add(1);
            add(3);
            add(4);
            add(6);
            add(9);

        }
    };
    @SuppressWarnings("serial")
    private List<Integer> forCtryCodePrefix9 = new ArrayList<Integer>() {
        {
            add(1);
            add(2);
            add(3);
            add(4);
            add(5);
            add(8);
        }
    };
    @SuppressWarnings("serial")
    private Map<Integer, List<Integer>> countryCodeMap = new HashMap<Integer, List<Integer>>() {
        {
            put(1, forCtryCodePrefix1);
            put(2, forCtryCodePrefix2);
            put(3, forCtryCodePrefix3);
            put(4, forCtryCodePrefix4);
            put(5, forCtryCodePrefix5);
            put(6, forCtryCodePrefix6);
            put(7, forCtryCodePrefix7);
            put(8, forCtryCodePrefix8);
            put(9, forCtryCodePrefix9);

        }
    };

    public int getCtryCode(String phoneNumber) {
        String ctryCodeAtIndex1 = phoneNumber.substring(1, 2);
        Integer ctryCode = 0;
        String ctryCodeStr = "0";

        List<Integer> ctryCodeList = countryCodeMap.get(Integer.valueOf(ctryCodeAtIndex1));
        if (ctryCodeList.isEmpty()) {
            ctryCode = Integer.valueOf(ctryCodeAtIndex1);
            return ctryCode.intValue();
        }
        String ctryCodeAtIndex2 = phoneNumber.substring(2, 3);
        for (Integer ctryCodePrefix : ctryCodeList) {
            if (Integer.valueOf(ctryCodeAtIndex2) == ctryCodePrefix) {
                ctryCodeStr = phoneNumber.substring(1, 3);
                ctryCode = Integer.valueOf(ctryCodeStr);
                return ctryCode.intValue();

            }
        }
        ctryCodeStr = phoneNumber.substring(1, 4);
        ctryCode = Integer.valueOf(ctryCodeStr);
        return ctryCode.intValue();
    }

}
