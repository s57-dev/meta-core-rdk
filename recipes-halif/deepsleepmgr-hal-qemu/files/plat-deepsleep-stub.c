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

#include <rdk/halif/deepsleep-manager/deepSleepMgr.h>

static int g_inited;
static DeepSleep_WakeupReason_t g_reason = DEEPSLEEP_WAKEUPREASON_UNKNOWN;
static unsigned int g_keycode;

DeepSleep_Return_Status_t PLAT_DS_INIT(void)
{
    if (g_inited)
        return DEEPSLEEPMGR_ALREADY_INITIALIZED;
    g_inited = 1;
    g_reason = DEEPSLEEP_WAKEUPREASON_UNKNOWN;
    g_keycode = 0;
    return DEEPSLEEPMGR_SUCCESS;
}

DeepSleep_Return_Status_t PLAT_DS_SetDeepSleep(uint32_t deep_sleep_timeout, bool *isGPIOWakeup, bool networkStandby)
{
    (void)deep_sleep_timeout;
    (void)networkStandby;
    if (!g_inited)
        return DEEPSLEEPMGR_NOT_INITIALIZED;
    if (isGPIOWakeup)
        *isGPIOWakeup = false;
    return DEEPSLEEPMGR_SUCCESS;
}

DeepSleep_Return_Status_t PLAT_DS_DeepSleepWakeup(void)
{
    if (!g_inited)
        return DEEPSLEEPMGR_NOT_INITIALIZED;
    return DEEPSLEEPMGR_SUCCESS;
}

DeepSleep_Return_Status_t PLAT_DS_GetLastWakeupReason(DeepSleep_WakeupReason_t *wakeupReason)
{
    if (!g_inited)
        return DEEPSLEEPMGR_NOT_INITIALIZED;
    if (!wakeupReason)
        return DEEPSLEEPMGR_INVALID_ARGUMENT;
    *wakeupReason = g_reason;
    return DEEPSLEEPMGR_SUCCESS;
}

DeepSleep_Return_Status_t PLAT_DS_GetLastWakeupKeyCode(DeepSleepMgr_WakeupKeyCode_Param_t *wakeupKeyCode)
{
    if (!g_inited)
        return DEEPSLEEPMGR_NOT_INITIALIZED;
    if (!wakeupKeyCode)
        return DEEPSLEEPMGR_INVALID_ARGUMENT;
    wakeupKeyCode->keyCode = g_keycode;
    return DEEPSLEEPMGR_SUCCESS;
}

DeepSleep_Return_Status_t PLAT_DS_TERM(void)
{
    if (!g_inited)
        return DEEPSLEEPMGR_NOT_INITIALIZED;
    g_inited = 0;
    return DEEPSLEEPMGR_SUCCESS;
}
