Feature: Transfer step validation

  @be
    @afterDeleteAllDocumentsFromCosmosDb
    @afterDeleteAllFilesFromAzureBlobStorage
  Scenario Outline: 02.02.01 Check retry flow with incorrect file expiration date
    Given publish message with expiration time = now+-5 from file '<filePath>' to Azure Control Transfer Service Bus topic with metadata code '<metadata>'
    Then verify that current transfer job has stage 'Transfer' and state 'Error' in Cosmos db after processing
    Given republish message with expiration time = now+5 from file '<retryFilePath>' to Azure Control Transfer Service Bus topic with metadata code '<metadata>' and current transfer job
    Then verify that current transfer job has stage 'Transfer' and state 'Completed' in Cosmos db after processing

    Examples:
      | metadata                           | filePath                                                               | retryFilePath                                                     |
      | MMS_US_Scores_To_QAC               | data/Events/InitiateStep/MMS/scores_valid_file.json                    | data/Events/TransferStep/MMS/scores_retry.json                    |
      | LinkUp_CoreCompanyAnalytics_To_QAC | data/Events/InitiateStep/LinkUp/core_company_analytics_valid_file.json | data/Events/TransferStep/LinkUp/core_company_analytics_retry.json |

  @azure @azure_local
    @afterDeleteAllDocumentsFromCosmosDb
    @afterDeleteAllFilesFromAzureBlobStorage
  Scenario Outline: 02.02.02 Check retry flow with incorrect file link
    Given publish message with expiration time = now+15 from file '<filePath>' to Azure Control Transfer Service Bus topic with metadata code '<metadata>'
    Then verify that current transfer job has stage 'Transfer' and state 'Error' in Cosmos db after processing
    Given republish message with expiration time = now+15 from file '<retryFilePath>' to Azure Control Transfer Service Bus topic with metadata code '<metadata>' and current transfer job
    Then verify that current transfer job has stage 'Transfer' and state 'Completed' in Cosmos db after processing

    Examples:
      | metadata                       | filePath                                                                           | retryFilePath                                                          |
      | MMS_US_Companies_To_QAC        | data/Events/TransferStep/MMS/companies_invalid_file_link.json                      | data/Events/TransferStep/MMS/companies_retry.json                      |
      | LinkUp_CompanyScrapeLog_To_QAC | data/Events/TransferStep/LinkUp/raw_company_scrape_log_full_invalid_file_link.json | data/Events/TransferStep/LinkUp/raw_company_scrape_log_full_retry.json |


  @azure @azure_local
    @afterDeleteAllDocumentsFromCosmosDb
  Scenario Outline: 02.02.03 Check that transfer step is failed with incorrect file size
    Given publish message with expiration time = now+15 from file '<filePath>' to Azure Control Transfer Service Bus topic with metadata code '<metadata>'
    Then verify that current transfer job has stage 'Transfer' and state 'Error' in Cosmos db after processing

    Examples:
      | metadata                          | filePath                                                                              |
      | MMS_US_Scores_To_QAC              | data/Events/TransferStep/MMS/scores_invalid_file_size.json                            |
      | LinkUp_PITCompanyReference_To_QAC | data/Events/TransferStep/LinkUp/raw_pit_company_reference_full_invalid_file_size.json |


  @azure @azure_local
    @afterDeleteAllDocumentsFromCosmosDb
  Scenario Outline: 02.02.04 Check that transfer step is failed with incorrect md5
    Given publish message with expiration time = now+15 from file '<filePath>' to Azure Control Transfer Service Bus topic with metadata code '<metadata>'
    Then verify that current transfer job has stage 'Transfer' and state 'Error' in Cosmos db after processing

    Examples:
      | metadata                 | filePath                                                         |
      | MMS_US_Companies_To_QAC  | data/Events/TransferStep/MMS/companies_invalid_md5.json          |
      | LinkUp_JobRecords_To_QAC | data/Events/TransferStep/LinkUp/raw_sample_jobs_invalid_md5.json |
