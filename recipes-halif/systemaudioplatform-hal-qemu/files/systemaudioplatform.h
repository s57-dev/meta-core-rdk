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

#ifndef SYSTEMAUDIO_PLATFORM
#define SYSTEMAUDIO_PLATFORM

#include <stdint.h>
#include <string>
#include <gst/gst.h>
#include <gst/audio/audio.h>

enum AudioType
{
    AudioType_None,
    PCM,
    MP3,
    WAV
};

enum SourceType
{
   SourceType_None,
   DATA,
   HTTPSRC,
   FILESRC,
   WEBSOCKET
};

enum PlayMode
{
    PlayMode_None,
    SYSTEM,
    APP
};

enum MixGain 
{
    MIXGAIN_PRIM,
    MIXGAIN_SYS,
    MIXGAIN_TTS 
};
	
void systemAudioInitialize();
void systemAudioDeinitialize();
    
//change primary audio volume
void systemAudioChangePrimaryVol(MixGain gain,int volume);
   
//SAP specific
void systemAudioSetDetectTime( int detectTimeMs); 
void systemAudioSetHoldTime( int holdTimeMs);
void systemAudioSetThreshold(double thresHold);
void systemAudioSetVolume(GstElement *audioVolume, AudioType audioType,
			  PlayMode playMode, int thisVol);

//Pipeline creation
bool systemAudioGeneratePipeline(GstElement *pipeline, GstElement *source,
				 GstElement *capsfilter, GstElement **audioSink,
  				 GstElement **audioVolume, AudioType type,
				 PlayMode mode, SourceType sourceType,
				 bool smartVolumeEnable);
#endif
