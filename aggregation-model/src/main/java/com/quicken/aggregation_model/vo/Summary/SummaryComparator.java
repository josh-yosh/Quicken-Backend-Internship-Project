package com.quicken.aggregation_model.vo.Summary;

import java.util.Comparator;

public enum SummaryComparator implements Comparator<Summary>{
    
    SUMMARY_COMPARATOR;
    
    @Override
    public int compare(Summary summary1, Summary summary2) {
        assert summary1 != null;
        assert summary2 != null;

        return summary1.getDate().compareTo(summary2.getDate());
    }
}
