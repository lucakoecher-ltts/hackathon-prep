import { Injectable } from '@angular/core';
import axios from 'axios';

export interface DataItem {
  id: number;
  name: string;
}

@Injectable({
  providedIn: 'root'
})
export class DataService {
  private apiUrl = 'https://jsonplaceholder.typicode.com/users';
  private data: DataItem[] = [];

  async fetchData(): Promise<DataItem[]> {
    try {
      const response = await axios.get(this.apiUrl);
      this.data = response.data.map((user: any) => ({
        id: user.id,
        name: user.name
      }));
      return this.data;
    } catch (error) {
      console.error('Error fetching data:', error);
      return [];
    }
  }

  addItem(name: string): void {
    const newItem: DataItem = {
      id: this.data.length + 1,
      name: name
    };
    this.data.push(newItem);
  }

  getData(): DataItem[] {
    return this.data;
  }
}