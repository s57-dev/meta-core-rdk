/* Auto-generated no-op HAL for qemuarm Core RDK. */
#include <stddef.h>
#include <string.h>
#include <stdbool.h>
#include <stdint.h>
#include "rdk/halif/ds-hal/dsError.h"
#include "rdk/halif/ds-hal/dsTypes.h"
#include "rdk/halif/ds-hal/dsAVDTypes.h"
#include "rdk/halif/ds-hal/dsAudio.h"
#include "rdk/halif/ds-hal/dsCompositeIn.h"
#include "rdk/halif/ds-hal/dsCompositeInTypes.h"
#include "rdk/halif/ds-hal/dsDisplay.h"
#include "rdk/halif/ds-hal/dsFPD.h"
#include "rdk/halif/ds-hal/dsFPDTypes.h"
#include "rdk/halif/ds-hal/dsHdmiIn.h"
#include "rdk/halif/ds-hal/dsHdmiInTypes.h"
#include "rdk/halif/ds-hal/dsHost.h"
#include "rdk/halif/ds-hal/dsVideoDevice.h"
#include "rdk/halif/ds-hal/dsVideoDeviceTypes.h"
#include "rdk/halif/ds-hal/dsVideoPort.h"
#include "rdk/halif/ds-hal/dsUtl.h"

/* Prefer success; fill out-params with zeros when pointers are provided. */

dsError_t dsAudioPortInit(void)
{
    return dsERR_NONE;
}

dsError_t dsAudioPortTerm(void)
{
    return dsERR_NONE;
}

dsError_t dsGetAudioPort(dsAudioPortType_t type, int index, intptr_t *handle)
{
    return dsERR_NONE;
}

dsError_t dsGetAudioFormat(intptr_t handle, dsAudioFormat_t *audioFormat)
{
    return dsERR_NONE;
}

dsError_t dsGetAudioCompression(intptr_t handle, int *compression)
{
    return dsERR_NONE;
}

dsError_t dsSetAudioCompression(intptr_t handle, int compression)
{
    return dsERR_NONE;
}

dsError_t dsGetDialogEnhancement(intptr_t handle, int *level)
{
    return dsERR_NONE;
}

dsError_t dsSetDialogEnhancement(intptr_t handle, int level)
{
    return dsERR_NONE;
}

dsError_t dsGetDolbyVolumeMode(intptr_t handle, bool *mode)
{
    return dsERR_NONE;
}

dsError_t dsSetDolbyVolumeMode(intptr_t handle, bool mode)
{
    return dsERR_NONE;
}

dsError_t dsGetIntelligentEqualizerMode(intptr_t handle, int *mode)
{
    return dsERR_NONE;
}

dsError_t dsSetIntelligentEqualizerMode(intptr_t handle, int mode)
{
    return dsERR_NONE;
}

dsError_t dsGetVolumeLeveller(intptr_t handle, dsVolumeLeveller_t* volLeveller)
{
    return dsERR_NONE;
}

dsError_t dsSetVolumeLeveller(intptr_t handle, dsVolumeLeveller_t volLeveller)
{
    return dsERR_NONE;
}

dsError_t dsGetBassEnhancer(intptr_t handle, int *boost)
{
    return dsERR_NONE;
}

dsError_t dsSetBassEnhancer(intptr_t handle, int boost)
{
    return dsERR_NONE;
}

dsError_t dsIsSurroundDecoderEnabled(intptr_t handle, bool *enabled)
{
    return dsERR_NONE;
}

dsError_t dsEnableSurroundDecoder(intptr_t handle, bool enabled)
{
    return dsERR_NONE;
}

dsError_t dsGetDRCMode(intptr_t handle, int *mode)
{
    return dsERR_NONE;
}

dsError_t dsSetDRCMode(intptr_t handle, int mode)
{
    return dsERR_NONE;
}

dsError_t dsGetSurroundVirtualizer(intptr_t handle, dsSurroundVirtualizer_t *virtualizer)
{
    return dsERR_NONE;
}

dsError_t dsSetSurroundVirtualizer(intptr_t handle, dsSurroundVirtualizer_t virtualizer)
{
    return dsERR_NONE;
}

dsError_t dsGetMISteering(intptr_t handle, bool *enabled)
{
    return dsERR_NONE;
}

dsError_t dsSetMISteering(intptr_t handle, bool enabled)
{
    return dsERR_NONE;
}

dsError_t dsGetGraphicEqualizerMode(intptr_t handle, int *mode)
{
    return dsERR_NONE;
}

dsError_t dsSetGraphicEqualizerMode(intptr_t handle, int mode)
{
    return dsERR_NONE;
}

dsError_t dsGetMS12AudioProfileList(intptr_t handle, dsMS12AudioProfileList_t* profiles)
{
    return dsERR_NONE;
}

dsError_t dsGetMS12AudioProfile(intptr_t handle, char *profile)
{
    return dsERR_NONE;
}

dsError_t dsGetSupportedARCTypes(intptr_t handle, int *types)
{
    return dsERR_NONE;
}

dsError_t dsAudioSetSAD(intptr_t handle, dsAudioSADList_t sad_list)
{
    return dsERR_NONE;
}

dsError_t dsAudioEnableARC(intptr_t handle, dsAudioARCStatus_t arcStatus)
{
    return dsERR_NONE;
}

dsError_t dsGetStereoMode(intptr_t handle, dsAudioStereoMode_t *stereoMode)
{
    return dsERR_NONE;
}

dsError_t dsSetStereoMode(intptr_t handle, dsAudioStereoMode_t mode)
{
    return dsERR_NONE;
}

dsError_t dsGetStereoAuto(intptr_t handle, int *autoMode)
{
    return dsERR_NONE;
}

dsError_t dsSetStereoAuto(intptr_t handle, int autoMode)
{
    return dsERR_NONE;
}

dsError_t dsGetAudioGain(intptr_t handle, float *gain)
{
    return dsERR_NONE;
}

dsError_t dsSetAudioGain(intptr_t handle, float gain)
{
    return dsERR_NONE;
}

dsError_t dsGetAudioLevel(intptr_t handle, float *level)
{
    return dsERR_NONE;
}

dsError_t dsSetAudioLevel(intptr_t handle, float level)
{
    return dsERR_NONE;
}

dsError_t dsGetAudioDelay(intptr_t handle, uint32_t *audioDelayMs)
{
    return dsERR_NONE;
}

dsError_t dsSetAudioDelay(intptr_t handle, const uint32_t audioDelayMs)
{
    return dsERR_NONE;
}

dsError_t dsSetAudioAtmosOutputMode(intptr_t handle, bool enable)
{
    return dsERR_NONE;
}

dsError_t dsGetSinkDeviceAtmosCapability(intptr_t handle, dsATMOSCapability_t *capability)
{
    return dsERR_NONE;
}

dsError_t dsIsAudioMute(intptr_t handle, bool *muted)
{
    return dsERR_NONE;
}

dsError_t dsIsAudioPortEnabled(intptr_t handle, bool *enabled)
{
    return dsERR_NONE;
}

dsError_t dsEnableAudioPort(intptr_t handle, bool enabled)
{
    return dsERR_NONE;
}

dsError_t dsEnableMS12Config(intptr_t handle, dsMS12FEATURE_t feature,const bool enable)
{
    return dsERR_NONE;
}

dsError_t dsEnableLEConfig(intptr_t handle, const bool enable)
{
    return dsERR_NONE;
}

dsError_t dsGetLEConfig(intptr_t handle, bool *enable)
{
    return dsERR_NONE;
}

dsError_t dsSetMS12AudioProfile(intptr_t handle, const char* profile)
{
    return dsERR_NONE;
}

dsError_t dsSetAudioMute(intptr_t handle, bool mute)
{
    return dsERR_NONE;
}

dsError_t dsIsAudioMSDecode(intptr_t handle, bool *HasMS11Decode)
{
    return dsERR_NONE;
}

dsError_t dsIsAudioMS12Decode(intptr_t handle, bool *hasMS12Decode)
{
    return dsERR_NONE;
}

dsError_t dsAudioOutIsConnected(intptr_t handle, bool* isConnected)
{
    return dsERR_NONE;
}

dsError_t dsAudioOutRegisterConnectCB(dsAudioOutPortConnectCB_t CBFunc)
{
    return dsERR_NONE;
}

dsError_t dsAudioFormatUpdateRegisterCB(dsAudioFormatUpdateCB_t cbFun)
{
    return dsERR_NONE;
}

dsError_t dsAudioAtmosCapsChangeRegisterCB(dsAtmosCapsChangeCB_t cbFun)
{
    return dsERR_NONE;
}

dsError_t dsGetAudioCapabilities(intptr_t handle, int *capabilities)
{
    return dsERR_NONE;
}

dsError_t dsGetMS12Capabilities(intptr_t handle, int *capabilities)
{
    return dsERR_NONE;
}

dsError_t dsSetAssociatedAudioMixing(intptr_t handle, bool mixing)
{
    return dsERR_NONE;
}

dsError_t dsGetAssociatedAudioMixing(intptr_t handle, bool *mixing)
{
    return dsERR_NONE;
}

dsError_t dsSetFaderControl(intptr_t handle, int mixerbalance)
{
    return dsERR_NONE;
}

dsError_t dsGetFaderControl(intptr_t handle, int* mixerbalance)
{
    return dsERR_NONE;
}

dsError_t dsSetPrimaryLanguage(intptr_t handle, const char* pLang)
{
    return dsERR_NONE;
}

dsError_t dsGetPrimaryLanguage(intptr_t handle, char* pLang)
{
    return dsERR_NONE;
}

dsError_t dsSetSecondaryLanguage(intptr_t handle, const char* sLang)
{
    return dsERR_NONE;
}

dsError_t dsGetSecondaryLanguage(intptr_t handle, char* sLang)
{
    return dsERR_NONE;
}

dsError_t dsSetAudioMixerLevels(intptr_t handle, dsAudioInput_t aInput, int volume)
{
    return dsERR_NONE;
}

dsError_t dsSetApplicationAudioConfig(intptr_t handle, const dsApplicationAudioConfig_t* audioConfig, bool enable)
{
    return dsERR_NONE;
}

dsError_t dsGetApplicationAudioConfig(intptr_t handle, const dsApplicationAudioConfig_t* audioConfig, bool *enable)
{
    return dsERR_NONE;
}

dsError_t dsGetApplicationAudioConfigList(intptr_t handle, dsApplicationAudioConfigList_t* audioConfigList)
{
    return dsERR_NONE;
}

dsError_t dsCompositeInInit(void)
{
    return dsERR_NONE;
}

dsError_t dsCompositeInTerm(void)
{
    return dsERR_NONE;
}

dsError_t dsCompositeInGetNumberOfInputs(uint8_t *pNumberOfInputs)
{
    return dsERR_NONE;
}

dsError_t dsCompositeInGetStatus(dsCompositeInStatus_t *pStatus)
{
    return dsERR_NONE;
}

dsError_t dsCompositeInSelectPort(dsCompositeInPort_t Port)
{
    return dsERR_NONE;
}

dsError_t dsCompositeInScaleVideo(int32_t x, int32_t y, int32_t width, int32_t height)
{
    return dsERR_NONE;
}

dsError_t dsCompositeInRegisterConnectCB(dsCompositeInConnectCB_t CBFunc)
{
    return dsERR_NONE;
}

dsError_t dsCompositeInRegisterSignalChangeCB(dsCompositeInSignalChangeCB_t CBFunc)
{
    return dsERR_NONE;
}

dsError_t dsCompositeInRegisterStatusChangeCB(dsCompositeInStatusChangeCB_t CBFunc)
{
    return dsERR_NONE;
}

dsError_t dsCompositeInRegisterVideoModeUpdateCB(dsCompositeInVideoModeUpdateCB_t CBFunc)
{
    return dsERR_NONE;
}

dsError_t dsDisplayInit(void)
{
    return dsERR_NONE;
}

dsError_t dsGetDisplay(dsVideoPortType_t vType, int index, intptr_t *handle)
{
    return dsERR_NONE;
}

dsError_t dsGetEDID(intptr_t handle, dsDisplayEDID_t *edid)
{
    return dsERR_NONE;
}

dsError_t dsGetEDIDBytes(intptr_t handle, unsigned char *edid, int *length)
{
    return dsERR_NONE;
}

dsError_t dsGetDisplayAspectRatio(intptr_t handle, dsVideoAspectRatio_t *aspectRatio)
{
    return dsERR_NONE;
}

dsError_t dsSetAllmEnabled(intptr_t handle, bool enabled)
{
    return dsERR_NONE;
}

dsError_t dsGetAllmEnabled(intptr_t handle, bool *enabled)
{
    return dsERR_NONE;
}

dsError_t dsSetAVIContentType(intptr_t handle, dsAviContentType_t contentType)
{
    return dsERR_NONE;
}

dsError_t dsGetAVIContentType(intptr_t handle, dsAviContentType_t* contentType)
{
    return dsERR_NONE;
}

dsError_t dsSetAVIScanInformation(intptr_t handle, dsAVIScanInformation_t scanInfo)
{
    return dsERR_NONE;
}

dsError_t dsGetAVIScanInformation(intptr_t handle, dsAVIScanInformation_t* scanInfo)
{
    return dsERR_NONE;
}

dsError_t dsDisplayTerm(void)
{
    return dsERR_NONE;
}

dsError_t dsRegisterDisplayEventCallback(intptr_t handle, dsDisplayEventCallback_t cb)
{
    return dsERR_NONE;
}

dsError_t dsFPInit(void)
{
    return dsERR_NONE;
}

dsError_t dsSetFPBlink(dsFPDIndicator_t eIndicator, unsigned int uBlinkDuration, unsigned int uBlinkIterations)
{
    return dsERR_NONE;
}

dsError_t dsSetFPBrightness(dsFPDIndicator_t eIndicator, dsFPDBrightness_t eBrightness)
{
    return dsERR_NONE;
}

dsError_t dsGetFPBrightness(dsFPDIndicator_t eIndicator, dsFPDBrightness_t *pBrightness)
{
    return dsERR_NONE;
}

dsError_t dsSetFPState(dsFPDIndicator_t eIndicator, dsFPDState_t state)
{
    return dsERR_NONE;
}

dsError_t dsGetFPState(dsFPDIndicator_t eIndicator, dsFPDState_t* state)
{
    return dsERR_NONE;
}

dsError_t dsSetFPColor(dsFPDIndicator_t eIndicator, dsFPDColor_t eColor)
{
    return dsERR_NONE;
}

dsError_t dsGetFPColor(dsFPDIndicator_t eIndicator, dsFPDColor_t *pColor)
{
    return dsERR_NONE;
}

dsError_t dsSetFPTime(dsFPDTimeFormat_t eTimeFormat, const unsigned int uHour, const unsigned int uMinutes)
{
    return dsERR_NONE;
}

dsError_t dsSetFPText(const char* pText)
{
    return dsERR_NONE;
}

dsError_t dsSetFPTextBrightness(dsFPDTextDisplay_t eIndicator, dsFPDBrightness_t eBrightness)
{
    return dsERR_NONE;
}

dsError_t dsGetFPTextBrightness(dsFPDTextDisplay_t eIndicator, dsFPDBrightness_t *eBrightness)
{
    return dsERR_NONE;
}

dsError_t dsFPEnableCLockDisplay(int enable)
{
    return dsERR_NONE;
}

dsError_t dsSetFPScroll(unsigned int uScrollHoldOnDur, unsigned int uHorzScrollIterations, unsigned int uVertScrollIterations)
{
    return dsERR_NONE;
}

dsError_t dsFPTerm(void)
{
    return dsERR_NONE;
}

dsError_t dsSetFPTimeFormat(dsFPDTimeFormat_t eTimeFormat)
{
    return dsERR_NONE;
}

dsError_t dsGetFPTimeFormat(dsFPDTimeFormat_t *pTimeFormat)
{
    return dsERR_NONE;
}

dsError_t dsSetFPDMode(dsFPDMode_t eMode)
{
    return dsERR_NONE;
}

dsError_t dsFPGetLEDState(dsFPDLedState_t* state)
{
    return dsERR_NONE;
}

dsError_t dsFPSetLEDState(dsFPDLedState_t state)
{
    return dsERR_NONE;
}

dsError_t dsFPGetSupportedLEDStates(unsigned int* states)
{
    return dsERR_NONE;
}

dsError_t dsHdmiInInit(void)
{
    return dsERR_NONE;
}

dsError_t dsHdmiInTerm(void)
{
    return dsERR_NONE;
}

dsError_t dsHdmiInGetNumberOfInputs(uint8_t *pNumberOfinputs)
{
    return dsERR_NONE;
}

dsError_t dsHdmiInGetStatus(dsHdmiInStatus_t *pStatus)
{
    return dsERR_NONE;
}

dsError_t dsHdmiInSelectPort(dsHdmiInPort_t Port, bool audioMix, dsVideoPlaneType_t evideoPlaneType,bool topMost)
{
    return dsERR_NONE;
}

dsError_t dsHdmiInScaleVideo(int32_t x, int32_t y, int32_t width, int32_t height)
{
    return dsERR_NONE;
}

dsError_t dsHdmiInSelectZoomMode(dsVideoZoom_t requestedZoomMode)
{
    return dsERR_NONE;
}

dsError_t dsHdmiInGetCurrentVideoMode(dsVideoPortResolution_t *resolution)
{
    return dsERR_NONE;
}

dsError_t dsHdmiInRegisterConnectCB(dsHdmiInConnectCB_t CBFunc)
{
    return dsERR_NONE;
}

dsError_t dsHdmiInRegisterSignalChangeCB(dsHdmiInSignalChangeCB_t CBFunc)
{
    return dsERR_NONE;
}

dsError_t dsHdmiInRegisterStatusChangeCB(dsHdmiInStatusChangeCB_t CBFunc)
{
    return dsERR_NONE;
}

dsError_t dsHdmiInRegisterVideoModeUpdateCB(dsHdmiInVideoModeUpdateCB_t CBFunc)
{
    return dsERR_NONE;
}

dsError_t dsHdmiInRegisterAllmChangeCB(dsHdmiInAllmChangeCB_t CBFunc)
{
    return dsERR_NONE;
}

dsError_t dsHdmiInRegisterAVLatencyChangeCB(dsAVLatencyChangeCB_t CBFunc)
{
    return dsERR_NONE;
}

dsError_t dsHdmiInRegisterAviContentTypeChangeCB(dsHdmiInAviContentTypeChangeCB_t CBFunc)
{
    return dsERR_NONE;
}

dsError_t dsIsHdmiARCPort(dsHdmiInPort_t iPort, bool *isArcPort)
{
    return dsERR_NONE;
}

dsError_t dsGetEDIDBytesInfo(dsHdmiInPort_t iHdmiPort, unsigned char *edid, int *length)
{
    return dsERR_NONE;
}

dsError_t dsGetHDMISPDInfo(dsHdmiInPort_t iHdmiPort, unsigned char *data)
{
    return dsERR_NONE;
}

dsError_t dsSetEdidVersion(dsHdmiInPort_t iHdmiPort, tv_hdmi_edid_version_t iEdidVersion)
{
    return dsERR_NONE;
}

dsError_t dsGetEdidVersion(dsHdmiInPort_t iHdmiPort, tv_hdmi_edid_version_t *iEdidVersion)
{
    return dsERR_NONE;
}

dsError_t dsGetAllmStatus(dsHdmiInPort_t iHdmiPort, bool *allmStatus)
{
    return dsERR_NONE;
}

dsError_t dsGetSupportedGameFeaturesList(dsSupportedGameFeatureList_t* features)
{
    return dsERR_NONE;
}

dsError_t dsGetAVLatency(int *audio_latency, int *video_latency)
{
    return dsERR_NONE;
}

dsError_t dsSetEdid2AllmSupport(dsHdmiInPort_t iHdmiPort, bool allmSupport)
{
    return dsERR_NONE;
}

dsError_t dsGetHdmiVersion(dsHdmiInPort_t iHdmiPort, dsHdmiMaxCapabilityVersion_t *maxCompatibilityVersion)
{
    return dsERR_NONE;
}

dsError_t dsHdmiInGetVRRSupport(dsHdmiInPort_t port, bool * vrrSupport)
{
    return dsERR_NONE;
}

dsError_t dsHdmiInSetVRRSupport(dsHdmiInPort_t port, bool vrrSupport)
{
    return dsERR_NONE;
}

dsError_t dsHdmiInRegisterVRRChangeCB(dsHdmiInVRRChangeCB_t cb)
{
    return dsERR_NONE;
}

dsError_t dsHdmiInGetVRRStatus(dsHdmiInPort_t port, dsHdmiInVrrStatus_t *vrrStatus)
{
    return dsERR_NONE;
}

dsError_t dsHostInit(void)
{
    return dsERR_NONE;
}

dsError_t dsHostTerm(void)
{
    return dsERR_NONE;
}

dsError_t dsGetCPUTemperature(float *cpuTemperature)
{
    return dsERR_NONE;
}

dsError_t dsGetSocIDFromSDK(char *socID)
{
    return dsERR_NONE;
}

dsError_t dsGetHostEDID(unsigned char *edid, int *length)
{
    return dsERR_NONE;
}

dsError_t dsVideoDeviceInit(void)
{
    return dsERR_NONE;
}

dsError_t dsGetVideoDevice(int index, intptr_t *handle)
{
    return dsERR_NONE;
}

dsError_t dsSetDFC(intptr_t handle, dsVideoZoom_t dfc)
{
    return dsERR_NONE;
}

dsError_t dsGetDFC(intptr_t handle, dsVideoZoom_t *dfc)
{
    return dsERR_NONE;
}

dsError_t dsVideoDeviceTerm(void)
{
    return dsERR_NONE;
}

dsError_t dsGetHDRCapabilities(intptr_t handle, int *capabilities)
{
    return dsERR_NONE;
}

dsError_t dsGetSupportedVideoCodingFormats(intptr_t handle, unsigned int * supported_formats)
{
    return dsERR_NONE;
}

dsError_t dsGetVideoCodecInfo(intptr_t handle, dsVideoCodingFormat_t codec, dsVideoCodecInfo_t * info)
{
    return dsERR_NONE;
}

dsError_t dsForceDisableHDRSupport(intptr_t handle, bool disable)
{
    return dsERR_NONE;
}

dsError_t dsSetFRFMode(intptr_t handle, int frfmode)
{
    return dsERR_NONE;
}

dsError_t dsGetFRFMode(intptr_t handle, int *frfmode)
{
    return dsERR_NONE;
}

dsError_t dsGetCurrentDisplayframerate(intptr_t handle, char *framerate)
{
    return dsERR_NONE;
}

dsError_t dsSetDisplayframerate(intptr_t handle, char *framerate)
{
    return dsERR_NONE;
}

dsError_t dsRegisterFrameratePreChangeCB(dsRegisterFrameratePreChangeCB_t CBFunc)
{
    return dsERR_NONE;
}

dsError_t dsRegisterFrameratePostChangeCB(dsRegisterFrameratePostChangeCB_t CBFunc)
{
    return dsERR_NONE;
}

dsError_t dsVideoPortInit(void)
{
    return dsERR_NONE;
}

dsError_t dsVideoPortTerm(void)
{
    return dsERR_NONE;
}

dsError_t dsGetVideoPort(dsVideoPortType_t type, int index, intptr_t *handle)
{
    return dsERR_NONE;
}

dsError_t dsIsVideoPortEnabled(intptr_t handle, bool *enabled)
{
    return dsERR_NONE;
}

dsError_t dsIsDisplayConnected(intptr_t handle, bool *connected)
{
    return dsERR_NONE;
}

dsError_t dsIsDisplaySurround(intptr_t handle, bool *surround)
{
    return dsERR_NONE;
}

dsError_t dsGetSurroundMode(intptr_t handle, int *surround)
{
    return dsERR_NONE;
}

dsError_t dsVideoFormatUpdateRegisterCB(dsVideoFormatUpdateCB_t cb)
{
    return dsERR_NONE;
}

dsError_t dsIsVideoPortActive(intptr_t handle, bool *active)
{
    return dsERR_NONE;
}

dsError_t dsEnableHDCP(intptr_t handle, bool contentProtect, char *hdcpKey, size_t keySize)
{
    return dsERR_NONE;
}

dsError_t dsIsHDCPEnabled(intptr_t handle, bool* pContentProtected)
{
    return dsERR_NONE;
}

dsError_t dsEnableVideoPort(intptr_t handle, bool enabled)
{
    return dsERR_NONE;
}

dsError_t dsSetResolution(intptr_t handle, dsVideoPortResolution_t *resolution)
{
    return dsERR_NONE;
}

dsError_t dsGetResolution(intptr_t handle, dsVideoPortResolution_t *resolution)
{
    return dsERR_NONE;
}

dsError_t dsRegisterHdcpStatusCallback(intptr_t handle, dsHDCPStatusCallback_t cb)
{
    return dsERR_NONE;
}

dsError_t dsGetHDCPStatus(intptr_t handle, dsHdcpStatus_t *status)
{
    return dsERR_NONE;
}

dsError_t dsGetHDCPProtocol(intptr_t handle, dsHdcpProtocolVersion_t *protocolVersion)
{
    return dsERR_NONE;
}

dsError_t dsGetHDCPReceiverProtocol(intptr_t handle, dsHdcpProtocolVersion_t *protocolVersion)
{
    return dsERR_NONE;
}

dsError_t dsGetHDCPCurrentProtocol(intptr_t handle, dsHdcpProtocolVersion_t *protocolVersion)
{
    return dsERR_NONE;
}

dsError_t dsGetTVHDRCapabilities(intptr_t handle, int *capabilities)
{
    return dsERR_NONE;
}

dsError_t dsSupportedTvResolutions(intptr_t handle, int *resolutions)
{
    return dsERR_NONE;
}

dsError_t dsSetForceDisable4KSupport(intptr_t handle, bool disable)
{
    return dsERR_NONE;
}

dsError_t dsGetForceDisable4KSupport(intptr_t handle, bool *disable)
{
    return dsERR_NONE;
}

dsError_t dsGetVideoEOTF(intptr_t handle, dsHDRStandard_t *video_eotf)
{
    return dsERR_NONE;
}

dsError_t dsGetMatrixCoefficients(intptr_t handle, dsDisplayMatrixCoefficients_t *matrix_coefficients)
{
    return dsERR_NONE;
}

dsError_t dsGetColorDepth(intptr_t handle, unsigned int* color_depth)
{
    return dsERR_NONE;
}

dsError_t dsGetColorSpace(intptr_t handle, dsDisplayColorSpace_t* color_space)
{
    return dsERR_NONE;
}

dsError_t dsGetQuantizationRange(intptr_t handle, dsDisplayQuantizationRange_t* quantization_range)
{
    return dsERR_NONE;
}

dsError_t dsGetCurrentOutputSettings(intptr_t handle, dsHDRStandard_t* video_eotf, dsDisplayMatrixCoefficients_t* matrix_coefficients, dsDisplayColorSpace_t* color_space, unsigned int* color_depth, dsDisplayQuantizationRange_t* quantization_range)
{
    return dsERR_NONE;
}

dsError_t dsIsOutputHDR(intptr_t handle, bool* hdr)
{
    return dsERR_NONE;
}

dsError_t dsResetOutputToSDR(void)
{
    return dsERR_NONE;
}

dsError_t dsSetHdmiPreference(intptr_t handle, dsHdcpProtocolVersion_t *hdcpCurrentProtocol)
{
    return dsERR_NONE;
}

dsError_t dsGetHdmiPreference(intptr_t handle, dsHdcpProtocolVersion_t *hdcpCurrentProtocol)
{
    return dsERR_NONE;
}

dsError_t dsGetIgnoreEDIDStatus(intptr_t handle, bool* status)
{
    return dsERR_NONE;
}

dsError_t dsSetBackgroundColor(intptr_t handle, dsVideoBackgroundColor_t color)
{
    return dsERR_NONE;
}

dsError_t dsSetForceHDRMode(intptr_t handle, dsHDRStandard_t mode)
{
    return dsERR_NONE;
}

dsError_t dsColorDepthCapabilities(intptr_t handle, unsigned int *colorDepthCapability)
{
    return dsERR_NONE;
}

dsError_t dsGetPreferredColorDepth(intptr_t handle, dsDisplayColorDepth_t *colorDepth)
{
    return dsERR_NONE;
}

dsError_t dsSetPreferredColorDepth(intptr_t handle,dsDisplayColorDepth_t colorDepth)
{
    return dsERR_NONE;
}
