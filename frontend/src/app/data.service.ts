import { Injectable } from '@angular/core';
import axios from 'axios';

export interface VehicleItem {
  vehicleId: string;
  status: string;
  timestamp?: string;
}

@Injectable({
  providedIn: 'root'
})
export class DataService {
  private baseUrl = 'http://localhost:8080/api/v1/eco-mode';
  private data: VehicleItem[] = [];

  async activateVehicle(vehicleId: string): Promise<any> {
    try {
      const response = await axios.put(`${this.baseUrl}/activate/${vehicleId}`);
      this.updateLocalData(vehicleId, 'ACTIVE');
      return response.data;
    } catch (error) {
      console.error('Error activating vehicle:', error);
      throw error;
    }
  }

  async deactivateVehicle(vehicleId: string): Promise<any> {
    try {
      const response = await axios.put(`${this.baseUrl}/deactivate/${vehicleId}`);
      this.updateLocalData(vehicleId, 'INACTIVE');
      return response.data;
    } catch (error) {
      console.error('Error deactivating vehicle:', error);
      throw error;
    }
  }

  private updateLocalData(vehicleId: string, status: string): void {
    const existingIndex = this.data.findIndex(item => item.vehicleId === vehicleId);
    const newItem: VehicleItem = {
      vehicleId,
      status,
      timestamp: new Date().toISOString()
    };
    
    if (existingIndex >= 0) {
      this.data[existingIndex] = newItem;
    } else {
      this.data.push(newItem);
    }
  }

  getData(): VehicleItem[] {
    return this.data;
  }
}