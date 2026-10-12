#include "rdk_gstreamer_utils.h"
#include "rdk_gstreamer_utils_soc.h"

namespace rdk_gstreamer_utils {

void initVirtualDisplayHeightandWidthFromPlatform_soc(unsigned int* h, unsigned int* w)
{
    if (h) *h = 1080;
    if (w) *w = 1920;
}
const char* getAudioDecoderName_soc() { return "fakesink"; }
const char* getAudioUnderflowSignalName_soc() { return "underflow"; }
const char* getVideoUnderflowSignalName_soc() { return "underflow"; }
bool IntialVolSettingNeeded_soc() { return false; }
bool IsAudioFadeSupported_soc() { return false; }
void EaseAudio_soc(double, uint32_t, rgu_Ease) {}
bool isPtsOffsetAdjustmentSupported_soc() { return false; }
int getPtsOffsetAdjustment_soc(const std::string&) { return 0; }
void setVideoProperty_soc(GstElement *) {}
void processAudioGap_soc(GstElement *, gint64, gint32, gint64, bool) {}
void enableAudioSwitch_soc(GstElement *) {}
GstElement * configureUIAudioSink_soc(bool) { return gst_element_factory_make("fakesink", NULL); }
bool isUIAudioVGAudioMixSupported_soc() { return false; }
unsigned int getNativeAudioFlag_soc() { return 0; }
void configAudioCap_soc(AudioAttributes *, bool *, bool, GstCaps **, bool) {}
bool performAudioTrackCodecChannelSwitch_soc(struct rdkGstreamerUtilsPlaybackGrp *, const void *, AudioAttributes *,
                                             uint32_t *, unsigned int *, llong *, const llong *, unsigned int *,
                                             GstCaps **, bool *, bool, GstElement *, bool *, bool)
{
    return false;
}
void setAppSrcParams_soc(GstElement *, MediaType) {}
void setPixelAspectRatio_soc(GstCaps **, GstCaps *, uint32_t, uint32_t) {}
void deepElementAdded_soc(struct rdkGstreamerUtilsPlaybackGrp *, GstBin *, GstBin *, GstElement *) {}
void audioMixerGetDeviceInfo_soc(uint32_t& preferredFrames, uint32_t& maximumFrames)
{
    preferredFrames = 0;
    maximumFrames = 0;
}
size_t audioMixerGetBufferDelay_soc(int64_t, int) { return 0; }
uint64_t audioMixerGetFifoSize_soc() { return 0; }
void audioMixerConfigurePipeline_soc(GstElement *, GstElement *, GstElement *, bool) {}
uint64_t audioMixerGetQueuedBytes_soc(uint64_t, uint64_t) { return 0; }
void setVideoSinkMode_soc(GstElement *) {}
void configVideoCap_soc(std::string, uint32_t, uint32_t, uint32_t, uint32_t, bool, gchar **) {}
void switchToAudioMasterMode_soc() {}
void setKeyFrameFlag_soc(GstBuffer *, bool) {}
bool getDelayTimerEnabled_soc() { return false; }
void SetAudioServerParam_soc(bool) {}
void constructLLAudioPlayer_soc(int, GstElement *, GstElement *, GstElement *, GstElement *, GstElement *) {}

int32_t Drmhal_Platform_Initialize_soc(std::string) { return 0; }
uint32_t Drmhal_DeleteDrmStore_soc(void *, std::string) { return 0; }
bool Drmhal_QueryBatchIDFromLicenseResponse_soc(void *, void *) { return false; }
bool Drmhal_bindCallbackPrecheck_soc(int) { return false; }
int32_t Drmhal_FetchOutputProtectionConfigData_soc(const void *, int, const void *, const void *, const void *,
                                                   max_resolution_update_cb)
{
    return 0;
}
int32_t Drmhal_PreDecrypt_soc(void *, void *, bool, int, void **, void *, int) { return 0; }

} // namespace rdk_gstreamer_utils
