package com.humana.pharmacy.common.service.impl;

import javax.sql.DataSource;

/**
 * A mock implementation of BaseServiceImpl;
 */
public class MockServiceImpl extends BaseServiceImpl {
    /**
     * Constructor of MockServiceImpl.
     *
     * @param epostrxDataSource  DataSource for epostrx database
     * @param workflowDataSource DataSource for epostrx_workflow database
     * @throws RuntimeException if epostrxDataSource or workflowDataSource is null
     */
    public MockServiceImpl(DataSource epostrxDataSource, DataSource workflowDataSource) {
        super(epostrxDataSource, workflowDataSource);
    }
}
