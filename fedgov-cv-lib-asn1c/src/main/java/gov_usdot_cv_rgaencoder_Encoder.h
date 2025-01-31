/*
 * Copyright (C) 2024 LEIDOS.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

#include <jni.h>
/* Header for class gov_usdot_cv_lib_asn1c_RGAMessage*/

#ifndef _Included_gov_usdot_cv_lib_asn1c_RGAMessage
#define _Included_gov_usdot_cv_lib_asn1c_RGAMessage
#ifdef __cplusplus
extern "C" {
#endif

/*
 * Class:     gov_usdot_cv_lib_asn1c_RGAMessage
 * Method:    encodeRGA
 */
JNIEXPORT jbyteArray JNICALL Java_gov_usdot_cv_rgaencoder_Encoder_encodeRGA
  (JNIEnv *, jint, jint);
#ifdef __cplusplus
}
#endif
#endif