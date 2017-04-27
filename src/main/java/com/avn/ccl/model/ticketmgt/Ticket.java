/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.model.ticketmgt;

/**
 *
 * @author user
 */
public class Ticket {

    private int ticketTypeId;
    private int priorityId;
    private int productId;
    private int assigneeId;
    private int contactId;
    private int status;
    private String subject;
    private String description;

    /**
     * @return the ticketTypeId
     */
    public int getTicketTypeId() {
        return ticketTypeId;
    }

    /**
     * @param ticketTypeId the ticketTypeId to set
     */
    public void setTicketTypeId(int ticketTypeId) {
        this.ticketTypeId = ticketTypeId;
    }

    /**
     * @return the priorityId
     */
    public int getPriorityId() {
        return priorityId;
    }

    /**
     * @param priorityId the priorityId to set
     */
    public void setPriorityId(int priorityId) {
        this.priorityId = priorityId;
    }

    /**
     * @return the productId
     */
    public int getProductId() {
        return productId;
    }

    /**
     * @param productId the productId to set
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * @return the assigneeId
     */
    public int getAssigneeId() {
        return assigneeId;
    }

    /**
     * @param assigneeId the assigneeId to set
     */
    public void setAssigneeId(int assigneeId) {
        this.assigneeId = assigneeId;
    }

    /**
     * @return the contactId
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * @param contactId the contactId to set
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
