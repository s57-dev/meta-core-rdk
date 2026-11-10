/*
 * If not stated otherwise in this file or this component's LICENSE file the
 * following copyright and licenses apply:
 *
 * Copyright 2026 RDK Management
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

#include <rdk/halif/power-manager/plat_power.h>

static int g_inited;
static PWRMgr_PowerState_t g_state = PWRMGR_POWERSTATE_ON;
static bool g_wakeup[PWRMGR_WAKEUPSRC_MAX];

pmStatus_t PLAT_INIT(void)
{
    if (g_inited)
        return PWRMGR_ALREADY_INITIALIZED;
    g_inited = 1;
    g_state = PWRMGR_POWERSTATE_ON;
    return PWRMGR_SUCCESS;
}

pmStatus_t PLAT_API_SetPowerState(PWRMgr_PowerState_t newState)
{
    if (!g_inited)
        return PWRMGR_NOT_INITIALIZED;
    if (newState >= PWRMGR_POWERSTATE_MAX)
        return PWRMGR_INVALID_ARGUMENT;
    g_state = newState;
    return PWRMGR_SUCCESS;
}

pmStatus_t PLAT_API_GetPowerState(PWRMgr_PowerState_t *curState)
{
    if (!g_inited)
        return PWRMGR_NOT_INITIALIZED;
    if (!curState)
        return PWRMGR_INVALID_ARGUMENT;
    *curState = g_state;
    return PWRMGR_SUCCESS;
}

pmStatus_t PLAT_API_SetWakeupSrc(PWRMGR_WakeupSrcType_t srcType, bool enable)
{
    if (!g_inited)
        return PWRMGR_NOT_INITIALIZED;
    if (srcType >= PWRMGR_WAKEUPSRC_MAX)
        return PWRMGR_INVALID_ARGUMENT;
    g_wakeup[srcType] = enable;
    return PWRMGR_SUCCESS;
}

pmStatus_t PLAT_API_GetWakeupSrc(PWRMGR_WakeupSrcType_t srcType, bool *enable)
{
    if (!g_inited)
        return PWRMGR_NOT_INITIALIZED;
    if (!enable || srcType >= PWRMGR_WAKEUPSRC_MAX)
        return PWRMGR_INVALID_ARGUMENT;
    *enable = g_wakeup[srcType];
    return PWRMGR_SUCCESS;
}

pmStatus_t PLAT_Reset(PWRMgr_PowerState_t newState)
{
    (void)newState;
    if (!g_inited)
        return PWRMGR_NOT_INITIALIZED;
    return PWRMGR_OPERATION_NOT_SUPPORTED;
}

pmStatus_t PLAT_TERM(void)
{
    if (!g_inited)
        return PWRMGR_NOT_INITIALIZED;
    g_inited = 0;
    return PWRMGR_SUCCESS;
}
