/*
 * Copyright © 2012 - 2018 camunda services GmbH and various authors (info@camunda.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.camunda.bpm.qa.upgrade;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngineConfiguration;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.qa.upgrade.json.CreateProcessInstanceWithJsonVariablesScenario;

public class TestFixture {

  public static final String ENGINE_VERSION = "7.8.0";

  public TestFixture(ProcessEngine processEngine) {
  }

  public static void main(String[] args) {
    ProcessEngineConfigurationImpl processEngineConfiguration = (ProcessEngineConfigurationImpl) ProcessEngineConfiguration
      .createProcessEngineConfigurationFromResource("camunda.cfg.xml");
    ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();

    // register test scenarios
    ScenarioRunner runner = new ScenarioRunner(processEngine, ENGINE_VERSION);

    // compensation
    runner.setupScenarios(CreateProcessInstanceWithJsonVariablesScenario.class);

    processEngine.close();
  }
}
