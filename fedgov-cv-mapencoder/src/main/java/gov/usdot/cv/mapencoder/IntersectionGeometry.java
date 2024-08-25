/*
 * Copyright (C) 2023 LEIDOS.
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
 
package gov.usdot.cv.mapencoder;

public class IntersectionGeometry {
    public String name;
    private boolean nameExists;
    private IntersectionReferenceID id;
    private boolean fullRdAuthIDExists;
    private String fullRdAuthID;
    private boolean relativeRdAuthIDExists;
    private String relativeRdAuthID;
    public int revision;
    private Position3D refPoint;
    private int laneWidth;
    private boolean laneWidthExists;
    private SpeedLimitList speedLimits;
    private boolean speedLimitsExists;
    private LaneList laneSet;
    private PreemptPriorityList preemptPriorityData;
    private boolean preemptPriorityDataExists;

    // Constructors
    public IntersectionGeometry() {
    }

    public IntersectionGeometry(String name, boolean nameExists, IntersectionReferenceID id, boolean fullRdAuthIDExists, String fullRdAuthID, boolean relativeRdAuthIDExists, String relativeRdAuthID, int revision,
                            Position3D refPoint, int laneWidth, boolean laneWidthExists,
                            SpeedLimitList speedLimits, boolean speedLimitsExists,
                            LaneList laneSet, PreemptPriorityList preemptPriorityData,
                            boolean preemptPriorityDataExists) {
        this.name = name;
        this.nameExists = nameExists;
        this.id = id;
        this.fullRdAuthIDExists = fullRdAuthIDExists;
        this.fullRdAuthID = fullRdAuthID;
        this.relativeRdAuthIDExists = relativeRdAuthIDExists;
        this.relativeRdAuthID = relativeRdAuthID;
        this.revision = revision;
        this.refPoint = refPoint;
        this.laneWidth = laneWidth;
        this.laneWidthExists = laneWidthExists;
        this.speedLimits = speedLimits;
        this.speedLimitsExists = speedLimitsExists;
        this.laneSet = laneSet;
        this.preemptPriorityData = preemptPriorityData;
        this.preemptPriorityDataExists = preemptPriorityDataExists;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isNameExists() {
        return nameExists;
    }

    public void setNameExists(boolean nameExists) {
        this.nameExists = nameExists;
    }

    public IntersectionReferenceID getId() {
        return id;
    }

    public void setId(IntersectionReferenceID id) {
        this.id = id;
    }

    public boolean isFullRdAuthIDExists() {
        return fullRdAuthIDExists;
    }

    public void setFullRdAuthIDExists(boolean fullRdAuthIDExists) {
        this.fullRdAuthIDExists = fullRdAuthIDExists;
    }

    public String getFullRdAuthID() {
        return fullRdAuthID;
    }

    public void setFullRdAuthID(String fullRdAuthID) {
        this.fullRdAuthID = fullRdAuthID;
    }

    public boolean isRelativeRdAuthIDExists() {
        return relativeRdAuthIDExists;
    }

    public void setRelativeRdAuthIDExists(boolean relativeRdAuthIDExists) {
        this.relativeRdAuthIDExists = relativeRdAuthIDExists;
    }

    public String getRelativeRdAuthID() {
        return relativeRdAuthID;
    }

    public void setRelativeRdAuthID(String relativeRdAuthID) {
        this.relativeRdAuthID = relativeRdAuthID;
    }

    public int getRevision() {
        return revision;
    }

    public void setRevision(int revision) {
        this.revision = revision;
    }

    public Position3D getRefPoint() {
        return refPoint;
    }

    public void setRefPoint(Position3D refPoint) {
        this.refPoint = refPoint;
    }

    public int getLaneWidth() {
        return laneWidth;
    }

    public void setLaneWidth(int laneWidth) {
        this.laneWidth = laneWidth;
    }

    public boolean isLaneWidthExists() {
        return laneWidthExists;
    }

    public void setLaneWidthExists(boolean laneWidthExists) {
        this.laneWidthExists = laneWidthExists;
    }

    public SpeedLimitList getSpeedLimits() {
        return speedLimits;
    }

    public void setSpeedLimits(SpeedLimitList speedLimits) {
        this.speedLimits = speedLimits;
    }

    public boolean isSpeedLimitsExists() {
        return speedLimitsExists;
    }

    public void setSpeedLimitsExists(boolean speedLimitsExists) {
        this.speedLimitsExists = speedLimitsExists;
    }

    public LaneList getLaneSet() {
        return laneSet;
    }

    public void setLaneSet(LaneList laneSet) {
        this.laneSet = laneSet;
    }

    public PreemptPriorityList getPreemptPriorityData() {
        return preemptPriorityData;
    }

    public void setPreemptPriorityData(PreemptPriorityList preemptPriorityData) {
        this.preemptPriorityData = preemptPriorityData;
    }

    public boolean isPreemptPriorityDataExists() {
        return preemptPriorityDataExists;
    }

    public void setPreemptPriorityDataExists(boolean preemptPriorityDataExists) {
        this.preemptPriorityDataExists = preemptPriorityDataExists;
    }

    @Override
    public String toString() {
        return "IntersectionGeometry{" +
                "name='" + name + '\'' +
                ", nameExists=" + nameExists +
                ", id=" + id +
                ", fullRdAuthIDExists=" + fullRdAuthIDExists +
                ", fullRdAuthID=" + fullRdAuthID +
                ", relativeRdAuthIDExists=" + relativeRdAuthIDExists +
                ", relativeRdAuthID=" + relativeRdAuthID + 
                ", revision=" + revision +
                ", refPoint=" + refPoint +
                ", laneWidth=" + laneWidth +
                ", laneWidthExists=" + laneWidthExists +
                ", speedLimits=" + speedLimits +
                ", speedLimitsExists=" + speedLimitsExists +
                ", laneSet=" + laneSet +
                ", preemptPriorityData=" + preemptPriorityData +
                ", preemptPriorityDataExists=" + preemptPriorityDataExists +
                '}';
    }
}
