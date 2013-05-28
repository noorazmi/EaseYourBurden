package com.project.parsers.data;

import java.util.ArrayList;

public class DebitsCreditsStatusData
{
    private ArrayList<DebitsData> debitsDataList;
    private ArrayList<DebitsData> creditsDataList;
    private ArrayList<DebitsData> statusDataList;
    private String totalDebitAmount;
    private String totalCreditAmount;
    private String totalStatusAmount;
    private String statusType;
    
    public ArrayList<DebitsData> getDebitsDataList()
    {
        return debitsDataList;
    }
    public void setDebitsDataList(ArrayList<DebitsData> debitsDataList)
    {
        this.debitsDataList = debitsDataList;
    }
    public ArrayList<DebitsData> getCreditsDataList()
    {
        return creditsDataList;
    }
    public void setCreditsDataList(ArrayList<DebitsData> creditsDataList)
    {
        this.creditsDataList = creditsDataList;
    }
    public ArrayList<DebitsData> getStatusDataList()
    {
        return statusDataList;
    }
    public void setStatusDataList(ArrayList<DebitsData> statusDataList)
    {
        this.statusDataList = statusDataList;
    }
    public String getTotalDebitAmount()
    {
        return totalDebitAmount;
    }
    public void setTotalDebitAmount(String totalDebits)
    {
        this.totalDebitAmount = totalDebits;
    }
    public String getTotalCreditAmount()
    {
        return totalCreditAmount;
    }
    public void setTotalCreditAmount(String totalCreditAmount)
    {
        this.totalCreditAmount = totalCreditAmount;
    }
    public String getTotalStatusAmount()
    {
        return totalStatusAmount;
    }
    public void setTotalStatusAmount(String totalStatusAmount)
    {
        this.totalStatusAmount = totalStatusAmount;
    }
    public String getStatusType()
    {
        return statusType;
    }
    public void setStatusType(String statusType)
    {
        this.statusType = statusType;
    }
    
    
    
}
