package com.project.parsers.data;

import java.util.ArrayList;

public class DebitsCreditsStatusData
{
    private ArrayList<DebitsData> debitsDataList;
    private String totalDebitAmount;
    
    public ArrayList<DebitsData> getDebitsDataList()
    {
        return debitsDataList;
    }
    public void setDebitsDataList(ArrayList<DebitsData> debitsDataList)
    {
        this.debitsDataList = debitsDataList;
    }
    public String getTotalDebitAmount()
    {
        return totalDebitAmount;
    }
    public void setTotalDebitAmount(String totalDebits)
    {
        this.totalDebitAmount = totalDebits;
    }
    
}
