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
