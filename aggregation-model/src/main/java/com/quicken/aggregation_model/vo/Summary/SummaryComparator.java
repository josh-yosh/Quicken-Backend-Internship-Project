package com.quicken.aggregation_model.vo.Summary;

import java.sql.Date;
import java.util.Comparator;

public enum SummaryComparator implements Comparator<Summary>{
    
    SUMMARY_COMPARATOR;
    
    @Override
    public int compare(Summary summary1, Summary summary2) {
        assert summary1 != null;
        assert summary2 != null;

        Date startDate1 = summary1.getDateRange().get(0);
        Date startDate2 = summary2.getDateRange().get(0);
        return startDate1.compareTo(startDate2);
    }
}
