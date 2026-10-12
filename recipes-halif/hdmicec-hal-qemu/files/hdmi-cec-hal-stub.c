/* No-op HDMI CEC HAL for qemuarm Core RDK. */
#include <ccec/drivers/hdmi_cec_driver.h>

HDMI_CEC_STATUS HdmiCecOpen(int *handle)
{
    if (handle)
        *handle = 1;
    return HDMI_CEC_IO_SUCCESS;
}

HDMI_CEC_STATUS HdmiCecClose(int handle)
{
    (void)handle;
    return HDMI_CEC_IO_SUCCESS;
}

HDMI_CEC_STATUS HdmiCecAddLogicalAddress(int handle, int logicalAddresses)
{
    (void)handle;
    (void)logicalAddresses;
    return HDMI_CEC_IO_SUCCESS;
}

HDMI_CEC_STATUS HdmiCecRemoveLogicalAddress(int handle, int logicalAddresses)
{
    (void)handle;
    (void)logicalAddresses;
    return HDMI_CEC_IO_SUCCESS;
}

HDMI_CEC_STATUS HdmiCecGetLogicalAddress(int handle, int *logicalAddress)
{
    (void)handle;
    if (logicalAddress)
        *logicalAddress = 0;
    return HDMI_CEC_IO_SUCCESS;
}

HDMI_CEC_STATUS HdmiCecGetPhysicalAddress(int handle, unsigned int *physicalAddress)
{
    (void)handle;
    if (physicalAddress)
        *physicalAddress = 0x1000;
    return HDMI_CEC_IO_SUCCESS;
}

HDMI_CEC_STATUS HdmiCecSetRxCallback(int handle, HdmiCecRxCallback_t cbfunc, void *data)
{
    (void)handle;
    (void)cbfunc;
    (void)data;
    return HDMI_CEC_IO_SUCCESS;
}

HDMI_CEC_STATUS HdmiCecSetTxCallback(int handle, HdmiCecTxCallback_t cbfunc, void *data)
{
    (void)handle;
    (void)cbfunc;
    (void)data;
    return HDMI_CEC_IO_SUCCESS;
}

HDMI_CEC_STATUS HdmiCecTx(int handle, const unsigned char *buf, int len, int *result)
{
    (void)handle;
    (void)buf;
    (void)len;
    if (result)
        *result = HDMI_CEC_IO_SENT_AND_ACKD;
    return HDMI_CEC_IO_SUCCESS;
}

HDMI_CEC_STATUS HdmiCecTxAsync(int handle, const unsigned char *buf, int len)
{
    (void)handle;
    (void)buf;
    (void)len;
    return HDMI_CEC_IO_SUCCESS;
}
